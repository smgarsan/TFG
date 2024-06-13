package com.tfg.agents.services;

import com.tfg.agents.dtos.*;
import com.tfg.agents.models.MapModel;
import com.tfg.agents.models.Obstacle;
import com.tfg.agents.repositories.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MapService {

    @Autowired
    MapRepository mapRepository;

    public Optional<MapResponseDto> getMap(String mapId) {
        Optional<MapModel> mapModelOptional = mapRepository.findById(mapId);
        if (mapModelOptional.isPresent()) {
            MapModel mapModel = mapModelOptional.get();
            MapResponseDto mapDto = new MapResponseDto(
                    mapModel.getName(),
                    mapModel.getSize(),
                    mapModel.getEnergy(),
                    mapModel.getAltitude(),
                    mapModel.getSpawn(),
                    mapModel.getTargets(),
                    mapModel.getObstacles()
            );

            return Optional.of(mapDto);
        }

        return Optional.empty();
    }

    public List<MapNameResponseDto> getNames() {
        List<MapModel> mapModels = mapRepository.findAll();
        return mapModels.stream()
                .map(mapModel -> {
                    MapNameResponseDto mapNameResponseDto = new MapNameResponseDto();
                    mapNameResponseDto.setId(mapModel.getId());
                    mapNameResponseDto.setName(mapModel.getName());
                    return mapNameResponseDto;
                })
                .collect(Collectors.toList());
    }

    public boolean deleteMap(String id) {
        try {
            mapRepository.deleteById(id);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public MapIdResponseDto saveMap(MapRequestDto mapDto) {
        MapModel mapModel = new MapModel();
        mapModel.setName(mapDto.getName());
        mapModel.setSize(mapDto.getSize());
        mapModel.setObstacles(mapDto.getObstacles());
        mapModel.setSpawn(mapDto.getSpawn());
        mapModel.setAltitude(mapDto.getAltitude());
        mapModel.setTargets(mapDto.getTargets());
        mapModel.setEnergy(mapDto.getEnergy());

        return new MapIdResponseDto(mapRepository.save(mapModel).getId());
    }

    public Optional<MapInfoResponseDto> getInfo(String id) {
        return mapRepository.findById(id)
                .map(mapModel -> {
                    MapInfoResponseDto responseDto = new MapInfoResponseDto();
                    responseDto.setName(mapModel.getName());
                    responseDto.setSize(mapModel.getSize());
                    responseDto.setSpawn(mapModel.getSpawn());
                    responseDto.setTargets(mapModel.getTargets());
                    responseDto.setEnergy(mapModel.getEnergy());
                    responseDto.setAltitude(mapModel.getAltitude());
                    return responseDto;
                });
    }

    public Optional<MapObstaclesResponseDto> getObstacles(String id) {
        return mapRepository.findById(id)
                .map(mapModel -> {
                   MapObstaclesResponseDto responseDto = new MapObstaclesResponseDto();
                   responseDto.setObstacles(mapModel.getObstacles());
                   return responseDto;
                });
    }

    public Optional<MapObstaclesResponseDto> getObstaclesNearPosition(String mapId, int x, int y, int distance) {
        if (distance <= 0 || !mapRepository.existsById(mapId)) return Optional.empty();
        MapInfoResponseDto info = getInfo(mapId).get();
        int rows = info.getSize().getRows();
        int columns = info.getSize().getColumns();
        if (x >= rows || y >= columns || x < 0 || y < 0) return Optional.empty();
        int minX = x - distance;
        int minY = y - distance;
        int maxX = x + distance;
        int maxY = y + distance;

        MapObstaclesResponseDto result = mapRepository.getObstaclesNearPosition(mapId, minX, minY, maxX, maxY);
        if (result == null) {
            ArrayList<Obstacle> emptyList = new ArrayList<>();
            return Optional.of(new MapObstaclesResponseDto(emptyList));
        }

        return Optional.of(result);

    }

}
