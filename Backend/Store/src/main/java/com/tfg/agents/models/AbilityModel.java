package com.tfg.agents.models;

import jakarta.persistence.*;

@Entity
@Table(name = "habilidad")
public class AbilityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habilidad")
    private Long abilityId;

    @Column(name = "nombre", nullable = false, length = 50)
    private String name;

    @Column(name = "descripcion", nullable = false)
    private String description;

    public Long getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(Long abilityId) {
        this.abilityId = abilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
