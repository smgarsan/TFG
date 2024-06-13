package com.tfg.agents.dtos;

public class AgentRequestDto {

    private String name;
    private String description;

    public AgentRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
