package com.tfg.environment.services;

import com.tfg.environment.dtos.*;
import com.tfg.environment.models.SessionModel;
import com.tfg.environment.models.UserModel;
import com.tfg.environment.repositories.SessionRepository;
import com.tfg.environment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SseService sseService;

    public Optional<SessionIdResponseDto> saveSession(SessionRequestDto data) {
        String mapUrl = "http://localhost:8080/maps/" + data.getMapId() + "/info";
        String agentUrl = "http://localhost:8080/agents/" + data.getAgentId();
        ResponseEntity<MapApiResponse> mapResponse = restTemplate.getForEntity(mapUrl, MapApiResponse.class);
        ResponseEntity<AgentApiResponse> agentResponse = restTemplate.getForEntity(agentUrl, AgentApiResponse.class);

        if (mapResponse.getStatusCode() == HttpStatus.OK && agentResponse.getStatusCode() == HttpStatus.OK &&
                userRepository.existsById(data.getUserId())) {
            MapApiResponse map = mapResponse.getBody();
            AgentApiResponse agent = agentResponse.getBody();
            SessionModel sessionModel = new SessionModel();
            sessionModel.setMapName(map.getName());
            sessionModel.setUserId(data.getUserId());
            sessionModel.setAgentId(data.getAgentId());
            sessionModel.setMapId(data.getMapId());
            sessionModel.setCurrentPosition(map.getSpawn());
            sessionModel.setTargets(map.getTargets());
            sessionModel.setCurrentTarget(map.getTargets().get(0));
            sessionModel.setEnergy(map.getEnergy());
            sessionModel.setAltitude(map.getAltitude());
            sessionModel.setAgentName(agent.getName());
            ArrayList<Integer> coordinate = map.getSpawn();
            ArrayList<ArrayList<Integer>> currentLog = new ArrayList<>();
            currentLog.add(coordinate);
            sessionModel.setLog(currentLog);
            sessionModel.setSensors(agent.getSensors());
            sessionModel.setAbilities(agent.getAbilities());
            sessionModel.setActive(true);
            SessionIdResponseDto dto = new SessionIdResponseDto(sessionRepository.save(sessionModel).getSessionId());
            return Optional.of(dto);
        }

        return Optional.empty();
    }

    public Optional<MapObstaclesResponseDto> getMiniMap(String sessionId, int distance) {
        SessionModel sessionModel = getSessionIfActive(sessionId);
        if (sessionModel != null) {
            int x = sessionModel.getCurrentPosition().get(0);
            int y = sessionModel.getCurrentPosition().get(1);
            String map = sessionModel.getMapId();
            String requestUrl = "http://localhost:8080/maps/" + map + "/obstacles/find?x="
                    + x + "&y=" + y + "&distance=" + distance;
            ResponseEntity<MapObstaclesResponseDto> response = restTemplate
                    .getForEntity(requestUrl, MapObstaclesResponseDto.class);

            return Optional.ofNullable(response.getBody());
        }

        return Optional.empty();
    }

    public Optional<MapObstaclesResponseDto> getSensorBasedObstacles(String sessionId) {
        Optional<MapObstaclesResponseDto> response = getMiniMap(sessionId, 1);
        if (response.isEmpty()) return Optional.empty();
        MapObstaclesResponseDto miniMap = response.get();
        ArrayList<Obstacle> obstacles = miniMap.getObstacles();
        if (obstacles.isEmpty()) return Optional.of(miniMap);
        SessionModel sessionModel = getSessionIfActive(sessionId);
        if (sessionModel == null) return Optional.empty();
        ArrayList<Integer> currentPosition = sessionModel.getCurrentPosition();
        int x = currentPosition.get(0);
        int y = currentPosition.get(1);
        ArrayList<Obstacle> sensorObstacles = new ArrayList<>();
        ArrayList<String> sensors = sessionModel.getSensors();
        for (String sensor : sensors) {
            switch(sensor) {
                case "FRONT":
                    hasObstacleAtPosition(obstacles, x, y + 1).ifPresent(sensorObstacles::add);
                    break;
                case "BACK":
                    hasObstacleAtPosition(obstacles, x, y - 1).ifPresent(sensorObstacles::add);
                    break;
                case "RIGHT":
                    hasObstacleAtPosition(obstacles, x + 1, y).ifPresent(sensorObstacles::add);
                    break;
                case "LEFT":
                    hasObstacleAtPosition(obstacles, x - 1, y).ifPresent(sensorObstacles::add);
                    break;
            }
        }

        return Optional.of(new MapObstaclesResponseDto(sensorObstacles));
    }

    private boolean hasObstacleAtPosition(SessionModel sessionModel, int x, int y) {
        Optional<MapObstaclesResponseDto> obstaclesOptional = getMiniMap(sessionModel.getSessionId(), 1);
        if (obstaclesOptional.isEmpty()) {
            return true;
        }

        ArrayList<Obstacle> obstacles = obstaclesOptional.get().getObstacles();
        return hasObstacleAtPosition(obstacles, x, y).isPresent();
    }

    private Optional<Obstacle> hasObstacleAtPosition(ArrayList<Obstacle> obstacles, int x, int y) {
        for (Obstacle obstacle : obstacles) {
            ArrayList<Integer> obstacleCoordinates = obstacle.getCoordinates();
            if (obstacleCoordinates.get(0) == x && obstacleCoordinates.get(1) == y) {
                return Optional.of(obstacle);
            }
        }

        return Optional.empty();
    }

    public MessageResponseDto performAbility(String sessionId, MoveRequestDto abilityDto) {
        SessionModel sessionModel = getSessionIfActive(sessionId);
        if (sessionModel == null) {
            return new MessageResponseDto("Session does not exist", false);
        }

        String ability = abilityDto.getAbility();
        ArrayList<String> abilities = sessionModel.getAbilities();
        if (!abilities.contains(ability)) {
            return new MessageResponseDto("Ability does not exist", false);
        }

        ArrayList<Integer> currentPosition = sessionModel.getCurrentPosition();
        ArrayList<Integer> nextPosition = generateNextPosition(currentPosition, ability);
        sessionModel.getLog().add(nextPosition);
        if (hasObstacleAtPosition(sessionModel, nextPosition.get(0), nextPosition.get(1))) {
            sessionRepository.save(sessionModel);
            return new MessageResponseDto("Ability could not be performed", false);
        }


        sessionModel.setCurrentPosition(nextPosition);
        sessionModel.setEnergy(sessionModel.getEnergy() - 1);
        String message = "Ability performed";
        if (isOnTarget(sessionModel)) {
            selectNextTarget(sessionModel);
            message = "Objective accomplished";
        }

        sessionRepository.save(sessionModel);

        SseResponseDto sseResponseDto = new SseResponseDto(sessionModel.getEnergy(), nextPosition);
        sseService.sendData(sessionId, sseResponseDto);

        if (sessionModel.getTargets().isEmpty()) {
                logOut(sessionId);
                return new MessageResponseDto("Mission accomplished", true);
            }

        return new MessageResponseDto(message, true);
    }

    private void selectNextTarget(SessionModel sessionModel) {
        ArrayList<ArrayList<Integer>> targets =  sessionModel.getTargets();
        if (!targets.isEmpty()) {
            sessionModel.setCurrentTarget(targets.get(0));
            targets.remove(0);
            sessionModel.setTargets(targets);
        }
    }

    private boolean isOnTarget(SessionModel sessionModel) {
        return Objects.equals(sessionModel.getCurrentPosition(), sessionModel.getCurrentTarget());
    }

    private ArrayList<Integer> generateNextPosition(ArrayList<Integer> currentPosition, String ability) {
        ArrayList<Integer> nextPosition = new ArrayList<>(currentPosition);
        switch (ability) {
            case "MOVE UP":
                nextPosition.set(1, nextPosition.get(1) + 1);
                break;
            case "MOVE DOWN":
                nextPosition.set(1, nextPosition.get(1) - 1);
                break;
            case "MOVE RIGHT":
                nextPosition.set(0, nextPosition.get(0) + 1);
                break;
            case "MOVE LEFT":
                nextPosition.set(0, nextPosition.get(0) - 1);
                break;
        }

        return nextPosition;
    }

    private void logOut(String sessionId) {
        Optional<SessionModel> sessionModelOptional = sessionRepository.findById(sessionId);
        if (sessionModelOptional.isPresent()) {
            SessionModel sessionModel = sessionModelOptional.get();
            sessionModel.setCurrentPosition(new ArrayList<>());
            sessionModel.setCurrentPosition(new ArrayList<>());
            sessionModel.setActive(false);
            sessionRepository.save(sessionModel);
        }
    }

    private SessionModel getSessionIfActive(String sessionId) {
        Optional<SessionModel> sessionOptional = sessionRepository.findById(sessionId);
        if (sessionOptional.isPresent()) {
            SessionModel session = sessionOptional.get();
            if (session.isActive()) {
                return session;
            }
        }
        return null;
    }

    public Optional<SessionResponseDto> getSession(String sessionId) {
        Optional<SessionModel> sessionModelOptional = sessionRepository.findById(sessionId);
        if (sessionModelOptional.isPresent()) {
            SessionModel sessionModel = sessionModelOptional.get();
            SessionResponseDto dto = new SessionResponseDto();
            Optional<UserModel> userModelOptional = userRepository.findById(sessionModel.getUserId());
            if (userModelOptional.isEmpty()) return Optional.empty();
            UserModel userModel = userModelOptional.get();
            dto.setUserName(userModel.getName() + " " + userModel.getSurname());
            dto.setAgentId(sessionModel.getAgentId());
            dto.setMapId(sessionModel.getMapId());
            dto.setEnergy(sessionModel.getEnergy());
            dto.setCurrentPosition(sessionModel.getCurrentPosition());
            dto.setMapName(sessionModel.getMapName());
            dto.setAgentName(sessionModel.getAgentName());

            return Optional.of(dto);
        }

        return Optional.empty();
    }

    public boolean deleteSession(String id) {
        try {
            Optional<SessionModel> sessionModelOptional = sessionRepository.findById(id);
            if (sessionModelOptional.isEmpty()) return false;
            if (sessionModelOptional.get().isActive()) {
                return false;
            } else {
                sessionRepository.deleteById(id);
            }

            return true;
        } catch (Exception error) {
            return false;

        }
    }
}
