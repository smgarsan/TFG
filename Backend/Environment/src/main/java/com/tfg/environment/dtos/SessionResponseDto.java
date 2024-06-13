package com.tfg.environment.dtos;

import java.util.ArrayList;

public class SessionResponseDto {

    private String userName;
    private String mapName;
    private String agentName;
    private Long agentId;
    private String mapId;
    private int energy;
    private ArrayList<Integer> currentPosition;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public ArrayList<Integer> getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(ArrayList<Integer> currentPosition) {
        this.currentPosition = currentPosition;
    }
}
