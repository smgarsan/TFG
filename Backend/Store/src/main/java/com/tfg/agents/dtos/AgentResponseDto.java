package com.tfg.agents.dtos;

import java.util.ArrayList;

public class AgentResponseDto {

    private Long id;

    private String name;

    private String description;

    private ArrayList<String> sensors;

    private ArrayList<String> abilities;

    public AgentResponseDto(Long id, String name, String description, ArrayList<String> sensors,
                            ArrayList<String> abilities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sensors = sensors;
        this.abilities = abilities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSensors(ArrayList<String> sensors) {
        this.sensors = sensors;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSensors() {
        return sensors;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
