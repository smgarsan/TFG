package com.tfg.environment.dtos;

import java.util.ArrayList;

public class SseResponseDto {

    private int energy;
    private ArrayList<Integer> currentPosition;

    public SseResponseDto(int energy, ArrayList<Integer> currentPosition) {
        this.energy = energy;
        this.currentPosition = currentPosition;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public ArrayList<Integer> getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(ArrayList<Integer> currentPosition) {
        this.currentPosition = currentPosition;
    }
}
