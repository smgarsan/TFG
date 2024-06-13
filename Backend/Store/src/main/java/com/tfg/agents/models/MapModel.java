package com.tfg.agents.models;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Document(collection = "maps")
public class MapModel {

    @Id
    private String id;
    private String name;
    private Size size;
    private Integer energy;
    private Integer altitude;
    private ArrayList<Integer> spawn;
    private ArrayList<ArrayList<Integer>> targets;
    private ArrayList<Obstacle> obstacles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
