package com.tfg.environment.controllers;

import com.tfg.environment.dtos.*;
import com.tfg.environment.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionIdResponseDto> saveSession(@RequestBody SessionRequestDto data) {
        try {
            Optional<SessionIdResponseDto> optional = sessionService.saveSession(data);
            return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionResponseDto> getSession(@PathVariable("id") String sessionId) {
        try {
            Optional<SessionResponseDto> optional = sessionService.getSession(sessionId);
            return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}/sensors")
    public ResponseEntity<MapObstaclesResponseDto> checkSensors(@PathVariable("id") String sessionId) {
        try {
            Optional<MapObstaclesResponseDto> optionalDto = sessionService
                    .getSensorBasedObstacles(sessionId);
            return optionalDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{id}/move")
    public ResponseEntity<MessageResponseDto> performAbility(@PathVariable("id") String sessionId,
                                                             @RequestBody MoveRequestDto ability) {
        try {
            MessageResponseDto response = sessionService.performAbility(sessionId, ability);
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            }

            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable("id") String sessionId) {
        try {
            return sessionService.deleteSession(sessionId) ? ResponseEntity.ok().build() :
                    ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
