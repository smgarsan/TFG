package com.tfg.environment.dtos;

import com.tfg.environment.services.Size;

import java.util.ArrayList;

public class MapApiResponse {
    private String name;
    private Size size;
    private ArrayList<Integer> spawn;
    private ArrayList<ArrayList<Integer>> targets;
    private Integer altitude;
    private Integer energy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ArrayList<Integer> getSpawn() {
        return spawn;
    }

    public void setSpawn(ArrayList<Integer> spawn) {
        this.spawn = spawn;
    }

    public ArrayList<ArrayList<Integer>> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<ArrayList<Integer>> targets) {
        this.targets = targets;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }
}
