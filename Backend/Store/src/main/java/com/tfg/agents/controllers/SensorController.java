package com.tfg.agents.controllers;

import com.tfg.agents.dtos.SensorIdResponseDto;
import com.tfg.agents.dtos.SensorRequestDto;
import com.tfg.agents.dtos.SensorResponseDto;
import com.tfg.agents.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    SensorService sensorService;

    @GetMapping
    public ResponseEntity<List<SensorResponseDto>> getSensors() {
        try {
            return ResponseEntity.ok(sensorService.getSensors());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<SensorIdResponseDto> saveSensor(@RequestBody SensorRequestDto sensor) {
        try {
            return ResponseEntity.ok().body(sensorService.saveSensor(sensor));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SensorResponseDto> getSensor (@PathVariable("id") Long id) {
        try {
            return sensorService.getSensor(id).map(
                    ResponseEntity::ok
            ).orElse(
                    ResponseEntity.notFound().build()
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable("id") Long id) {
        try {
            return sensorService.deleteSensor(id) ? ResponseEntity.ok().build() :
                    ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Void> updateSensor(@PathVariable("id") Long id, @RequestBody SensorRequestDto data) {
        try {
            return sensorService.updateSensor(id, data) ? ResponseEntity.ok().build() :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
