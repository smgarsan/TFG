package com.tfg.environment.dtos;
public class MessageResponseDto {

    private String status;
    private boolean success;

    public MessageResponseDto(String status, boolean success) {
        this.status = status;
        this.success = success;
    }
    public String getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return success;
    }
}
