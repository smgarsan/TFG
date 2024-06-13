package com.tfg.environment.dtos;

import java.awt.*;
import java.util.ArrayList;

public class SessionRequestDto {

    private String userId;
    private Long agentId;
    private String mapId;

    public String getUserId() {
        return userId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public String getMapId() {
        return mapId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }
}
