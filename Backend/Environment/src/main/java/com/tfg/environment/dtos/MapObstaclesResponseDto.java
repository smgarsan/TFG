package com.tfg.environment.dtos;

import com.tfg.environment.services.Obstacle;

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
