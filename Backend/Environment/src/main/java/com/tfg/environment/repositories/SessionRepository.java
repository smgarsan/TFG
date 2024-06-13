package com.tfg.environment.repositories;

import com.tfg.environment.models.SessionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends MongoRepository<SessionModel, String> {

}
