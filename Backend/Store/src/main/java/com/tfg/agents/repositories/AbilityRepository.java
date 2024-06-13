package com.tfg.agents.repositories;

import com.tfg.agents.models.AbilityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityRepository extends JpaRepository<AbilityModel, Long> {

}
