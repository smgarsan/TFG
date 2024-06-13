package com.tfg.agents.repositories;

import com.tfg.agents.dtos.MapObstaclesResponseDto;
import com.tfg.agents.models.MapModel;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository extends MongoRepository<MapModel, String> {

    @Aggregation(pipeline = {
            "{$match: { '_id': ?0 }}",
            "{$unwind: '$obstacles' }",
            "{$match: { 'obstacles.coordinates': { $geoWithin: { $box: [[?1, ?2], [?3, ?4]] } } }}",
            "{$group: { _id: null, obstacles: { $push: '$obstacles' } }}",
            "{$project: { _id: 0, obstacles: 1 }}"
    })
MapObstaclesResponseDto getObstaclesNearPosition(String id, int minX, int minY,
                                               int maxX, int maxY);

}
