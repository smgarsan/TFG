package com.tfg.agents.controllers;

import com.tfg.agents.dtos.*;
import com.tfg.agents.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    AgentService agentService;

    @GetMapping
    public ResponseEntity<ArrayList<AgentResponseDto>> getAgents() {
        try {
            return ResponseEntity.ok(agentService.getAgents());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<AgentIdResponseDto> saveAgent(@RequestBody AgentRequestDto agent) {
        try {
            return ResponseEntity.ok(agentService.saveAgent(agent));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AgentResponseDto> getAgent(@PathVariable("id") Long id) {
        try {
            return agentService.getAgent(id).map(
                    ResponseEntity::ok
            ).orElse(
                    ResponseEntity.notFound().build()
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable("id") Long id) {
        try {
            return agentService.deleteAgente(id) ? ResponseEntity.ok().build() :
                    ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/sensor")
    public ResponseEntity<Void> addSensor(@RequestBody AgentSensorDto data) {
        try {
            if (agentService.addSensor(data)) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/ability")
    public ResponseEntity<Void> addAbility(@RequestBody AgentAbilityDto data) {
        try {
            return agentService.addAbility(data) ? ResponseEntity.status(HttpStatus.OK).build() :
                    ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/sensor")
    public ResponseEntity<Void> deleteSensor(@RequestBody AgentSensorDto data) {
        try {
            return agentService.deleteSensor(data) ? ResponseEntity.noContent().build() :
            ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/ability")
    public ResponseEntity<Void> deleteAbility(@RequestBody AgentAbilityDto data) {
        try {
            return agentService.deleteAbility(data) ? ResponseEntity.noContent().build() :
                    ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Void> updateAgent(@PathVariable("id") Long id, @RequestBody AgentRequestDto data) {
        try {
            return agentService.updateAgent(id, data) ? ResponseEntity.ok().build() :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}