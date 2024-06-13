package com.tfg.agents.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "agente")
public class AgentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agente")
    private Long agentId;

    @Column(name = "nombre", nullable = false, length = 50)
    private String name;

    @Column(name = "descripcion", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "agente_sensor",
            joinColumns = @JoinColumn(name = "idAgente"),
            inverseJoinColumns = @JoinColumn(name = "idSensor")
    )
    private Set<SensorModel> sensors;

    @ManyToMany
    @JoinTable(
            name = "agente_habilidad",
            joinColumns = @JoinColumn(name = "idAgente"),
            inverseJoinColumns = @JoinColumn(name = "idHabilidad")
    )
    private Set<AbilityModel> abilities;

    public void setSensor(SensorModel sensor) {
        this.sensors.add(sensor);
    }

    public Set<SensorModel> getSensors() {
        return sensors;
    }

    public void setSensores(Set<SensorModel> sensors) {
        this.sensors = sensors;
    }

    public Set<AbilityModel> getAbilities() {
        return abilities;
    }

    public void setAbility(AbilityModel ability) {
        this.abilities.add(ability);
    }

    public void setAbilities(Set<AbilityModel> abilities) {
        this.abilities = abilities;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
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
