package com.tfg.agents.models;

import java.util.ArrayList;

public class Obstacle {

    private String type;

    private ArrayList<Integer> coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Obstacle() {
    }

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Integer> coordinates) {
        this.coordinates = coordinates;
    }

}
