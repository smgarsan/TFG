package com.tfg.agents.dtos;

import com.tfg.agents.models.Obstacle;

import java.util.ArrayList;

public class MapObstaclesResponseDto {

    private ArrayList<Obstacle> obstacles;

    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public MapObstaclesResponseDto(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public MapObstaclesResponseDto() {
    }
}
