package com.tfg.agents.models;

import jakarta.persistence.*;

@Entity
@Table(name = "sensor")
public class SensorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sensor")
    private Long sensorId;

    @Column(name = "nombre", nullable = false, length = 50)
    private String name;

    @Column(name = "descripcion", nullable = false)
    private String description;

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
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
