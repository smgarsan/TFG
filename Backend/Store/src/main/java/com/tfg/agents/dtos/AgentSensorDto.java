package com.tfg.agents.dtos;

public class AgentSensorDto {

    private Long agentId;
    private Long sensorId;

    public AgentSensorDto(Long agentId, Long sensorId) {
        this.agentId = agentId;
        this.sensorId = sensorId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public Long getSensorId() {
        return sensorId;
    }


}
