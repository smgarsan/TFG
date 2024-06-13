package com.tfg.agents.dtos;

public class AgentAbilityDto {

    private Long agentId;
    private Long abilityId;

    public AgentAbilityDto(Long agentId, Long abilityId) {
        this.agentId = agentId;
        this.abilityId = abilityId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public Long getAbilityId() {
        return abilityId;
    }

}