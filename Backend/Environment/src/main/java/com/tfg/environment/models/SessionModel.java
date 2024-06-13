package com.tfg.environment.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;

@Document(collection = "sessions")
public class SessionModel {

    @Id
    private String sessionId;
    private boolean isActive;
    private String userId;
    private Long agentId;
    private String mapId;
    private ArrayList<Integer> currentPosition;
    private ArrayList<Integer> currentTarget;
    private ArrayList<ArrayList<Integer>> targets;
    private int energy;
    private int altitude;
    private ArrayList<String> sensors;
    private ArrayList<String> abilities;
    private ArrayList<ArrayList<Integer>> log;
    private String agentName;
    private String mapName;

    public ArrayList<Integer> getCurrentTarget() {
        return currentTarget;
    }

    public void setCurrentTarget(ArrayList<Integer> currentTarget) {
        this.currentTarget = currentTarget;
    }

    public ArrayList<ArrayList<Integer>> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<ArrayList<Integer>> targets) {
        this.targets = targets;
    }

    public String getAgentName() {
        return agentName;
    }

    public String getMapName() {
        return mapName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ArrayList<String> getSensors() {
        return sensors;
    }

    public void setSensors(ArrayList<String> sensors) {
        this.sensors = sensors;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public ArrayList<Integer> getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(ArrayList<Integer> currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public ArrayList<ArrayList<Integer>> getLog() {
        return log;
    }

    public void setLog(ArrayList<ArrayList<Integer>> log) {
        this.log = log;
    }

}
