package com.tfg.environment.services;

import com.tfg.environment.dtos.SseResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseService {
    private final Map<String, List<SseEmitter>> sessionEmitters = new ConcurrentHashMap<>();

    public SseEmitter createSseEmitter(String sessionId) {
        SseEmitter emitter = new SseEmitter(600000L);
        sessionEmitters.computeIfAbsent(sessionId, key -> new ArrayList<>()).add(emitter);
        emitter.onCompletion(() -> removeEmitter(sessionId, emitter));
        return emitter;
    }

    public void sendData(String sessionId, SseResponseDto data) {
        List<SseEmitter> emitters = sessionEmitters.get(sessionId);
        if (emitters != null) {
            emitters.forEach(emitter -> {
                try {
                    emitter.send(data);
                } catch (Exception e) {
                    System.out.println("Error al enviar datos a un emisor");
                }
            });
        }
    }

    private void removeEmitter(String sessionId, SseEmitter emitter) {
        List<SseEmitter> emitters = sessionEmitters.get(sessionId);
        if (emitters != null) {
            emitters.remove(emitter);
            if (emitters.isEmpty()) {
                sessionEmitters.remove(sessionId);
            }
        }
    }
}
