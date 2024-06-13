package com.tfg.agents.repositories;

import com.tfg.agents.models.SensorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<SensorModel, Long> {

}
