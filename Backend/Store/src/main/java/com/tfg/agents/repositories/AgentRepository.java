package com.tfg.agents.repositories;

import com.tfg.agents.models.AgentModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<AgentModel, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM agente_sensor WHERE id_agente = ?1 AND id_sensor = ?2", nativeQuery = true)
    int deleteSensor(Long agentId, Long sensorId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM agente_habilidad WHERE id_agente = ?1 AND id_habilidad = ?2", nativeQuery = true)
    int deleteAbility(Long agentId, Long abilityId);
}
