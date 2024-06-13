package com.tfg.agents.dtos;

public class SensorRequestDto {

    private String name;
    private String description;

    public SensorRequestDto(String name, String description) {
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
