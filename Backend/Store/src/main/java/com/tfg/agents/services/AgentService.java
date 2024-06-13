package com.tfg.agents.services;

import com.tfg.agents.dtos.*;
import com.tfg.agents.models.AgentModel;
import com.tfg.agents.models.AbilityModel;
import com.tfg.agents.models.SensorModel;
import com.tfg.agents.repositories.AgentRepository;
import com.tfg.agents.repositories.AbilityRepository;
import com.tfg.agents.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgentService {

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    AbilityRepository abilityRepository;

    private static ArrayList<String> obtenerNombresSensores(AgentModel agentModel) {
        return agentModel.getSensors().stream()
                .map(SensorModel::getName)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<String> obtenerNombresHabilidades(AgentModel agentModel) {
        return agentModel.getAbilities().stream()
                .map(AbilityModel::getName)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    public ArrayList<AgentResponseDto> getAgents() {
        ArrayList<AgentModel> agentModelList = (ArrayList<AgentModel>) agentRepository.findAll();
        ArrayList<AgentResponseDto> agentDtoList = new ArrayList<>();
        for (AgentModel agentModel : agentModelList) {
            ArrayList<String> sensorsNames = obtenerNombresSensores(agentModel);
            ArrayList<String> abilitiesNames = obtenerNombresHabilidades(agentModel);
            AgentResponseDto agentDto = new AgentResponseDto(
                    agentModel.getAgentId(),
                    agentModel.getName(),
                    agentModel.getDescription(),
                    sensorsNames,
                    abilitiesNames
            );

            agentDtoList.add(agentDto);
        }

        return agentDtoList;
    }

    public AgentIdResponseDto saveAgent(AgentRequestDto agentDto) {
        AgentModel model = new AgentModel();
        model.setName(agentDto.getName());
        model.setDescription(agentDto.getDescription());
        return new AgentIdResponseDto(agentRepository.save(model).getAgentId());
    }

    public Optional<AgentResponseDto> getAgent(Long id) {
        return agentRepository.findById(id)
                .map(agentModel -> new AgentResponseDto(
                        agentModel.getAgentId(),
                        agentModel.getName(),
                        agentModel.getDescription(),
                        obtenerNombresSensores(agentModel),
                        obtenerNombresHabilidades(agentModel)
                ));
    }

    public boolean deleteAgente(Long id) {
        try {
            agentRepository.deleteById(id);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public boolean addSensor(AgentSensorDto data) {
        Optional<AgentModel> optionalAgent = agentRepository.findById(data.getAgentId());
        Optional<SensorModel> optionalSensor = sensorRepository.findById(data.getSensorId());
        if (optionalAgent.isPresent() && optionalSensor.isPresent()) {
            optionalAgent.ifPresent(agent-> optionalSensor.ifPresent(agent::setSensor));
            agentRepository.save(optionalAgent.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean addAbility(AgentAbilityDto data) {
        Optional<AgentModel> optionalAgent = agentRepository.findById(data.getAgentId());
        Optional<AbilityModel> optionalAbility = abilityRepository.findById(data.getAbilityId());
        if (optionalAgent.isPresent() && optionalAbility.isPresent()) {
            optionalAgent.ifPresent(agent -> optionalAbility.ifPresent(agent::setAbility));
            agentRepository.save(optionalAgent.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteSensor(AgentSensorDto data) {
        int aux = agentRepository.deleteSensor(data.getAgentId(), data.getSensorId());
        return aux != 0;
    }

    public boolean deleteAbility(AgentAbilityDto data) {
        int aux = agentRepository.deleteAbility(data.getAgentId(), data.getAbilityId());
        return aux != 0;
    }

    public boolean updateAgent(Long id, AgentRequestDto data) {
        Optional<AgentModel> optionalAgentModel = agentRepository.findById(id);
        if (optionalAgentModel.isPresent()) {
            AgentModel model = optionalAgentModel.get();
            if (data.getName() != null) model.setName(data.getName());
            if (data.getDescription() != null) model.setDescription(data.getDescription());
            agentRepository.save(model);
            return true;
        }

        return false;
    }
}
