package com.tfg.agents.dtos;

import com.tfg.agents.models.Obstacle;
import com.tfg.agents.models.Size;

import java.util.ArrayList;

public class MapRequestDto {

    private String name;
    private Size size;
    private ArrayList<Obstacle> obstacles;
    private Integer energy;
    private Integer altitude;
    private ArrayList<Integer> spawn;
    private ArrayList<ArrayList<Integer>> targets;

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
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

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

}
