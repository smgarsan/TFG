package com.tfg.agents.dtos;

import com.tfg.agents.models.Obstacle;
import com.tfg.agents.models.Size;

import java.util.ArrayList;

public class MapResponseDto {

    private String name;
    private Size size;
    private Integer energy;
    private Integer altitude;
    private ArrayList<Integer> spawn;
    private ArrayList<ArrayList<Integer>> targets;
    private ArrayList<Obstacle> obstacles;

    public MapResponseDto(String name, Size size, Integer energy, Integer altitude,
                          ArrayList<Integer> spawn, ArrayList<ArrayList<Integer>> target,
                          ArrayList<Obstacle> obstacles) {
        this.name = name;
        this.size = size;
        this.energy = energy;
        this.altitude = altitude;
        this.spawn = spawn;
        this.targets = target;
        this.obstacles = obstacles;
    }

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

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

}
