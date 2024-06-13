package com.tfg.environment.controllers;

import com.tfg.environment.services.SseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequestMapping("/sse")
public class SseController {

    @Autowired
    SseService sseService;

    @GetMapping("/connect/{id}")
    public ResponseEntity<SseEmitter> stream(@PathVariable("id") String sessionId) {
        try{
            return ResponseEntity.ok(sseService.createSseEmitter(sessionId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
