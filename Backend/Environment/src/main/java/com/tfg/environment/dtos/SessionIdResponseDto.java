package com.tfg.environment.dtos;

public class SessionIdResponseDto {

    private String sessionId;

    public SessionIdResponseDto(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}
