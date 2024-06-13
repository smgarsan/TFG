package com.tfg.agents.services;

import com.tfg.agents.dtos.SensorIdResponseDto;
import com.tfg.agents.dtos.SensorRequestDto;
import com.tfg.agents.dtos.SensorResponseDto;
import com.tfg.agents.models.SensorModel;
import com.tfg.agents.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public List<SensorResponseDto> getSensors() {
        return sensorRepository.findAll().stream()
                .map(sensorModel -> new SensorResponseDto(
                        sensorModel.getSensorId(),
                        sensorModel.getName(),
                        sensorModel.getDescription()
                )).collect(Collectors.toList());
    }

    public SensorIdResponseDto saveSensor(SensorRequestDto sensorDto) {
        SensorModel model = new SensorModel();
        model.setName(sensorDto.getName());
        model.setDescription(sensorDto.getDescription());
        return new SensorIdResponseDto(sensorRepository.save(model).getSensorId());
    }

    public Optional<SensorResponseDto> getSensor(Long id) {
        return sensorRepository.findById(id)
                .map(sensorModel -> new SensorResponseDto(
                        sensorModel.getSensorId(),
                        sensorModel.getName(),
                        sensorModel.getDescription()
                ));
    }

    public boolean deleteSensor(Long id) {
        if (!sensorRepository.existsById(id)) {
            return false;
        }

        sensorRepository.deleteById(id);
        return true;
    }

    public boolean updateSensor(Long id, SensorRequestDto data) {
        Optional<SensorModel> optionalSensorModel = sensorRepository.findById(id);
        if (optionalSensorModel.isPresent()) {
            SensorModel model = optionalSensorModel.get();
            if (data.getName() != null) model.setName((data.getName()));
            if (data.getDescription() != null) model.setDescription(data.getDescription());
            sensorRepository.save(model);
            return true;
        }

        return false;
    }

}
