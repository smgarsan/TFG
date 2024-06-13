package com.tfg.environment.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserModel {

    @Id
    private String identityChip;
    private String name;
    private String surname;
    private String dni;
    private Integer group;

    public String getIdentityChip() {
        return identityChip;
    }

    public void setIdentityChip(String identityChip) {
        this.identityChip = identityChip;
    }

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

}
