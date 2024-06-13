package com.tfg.environment.dtos;

public class UserResponseDto {

    private String name;
    private String surname;
    private String dni;
    private Integer group;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public UserResponseDto(String name, String surname, String dni, Integer group) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.group = group;
    }
}
