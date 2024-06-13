package com.tfg.environment.dtos;

import java.util.ArrayList;

public class AgentApiResponse {
    private String name;
    private String description;
    private ArrayList<String> sensors;
    private ArrayList<String> abilities;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
