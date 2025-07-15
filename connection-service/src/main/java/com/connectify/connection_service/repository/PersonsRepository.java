package com.connectify.connection_service.repository;

import com.connectify.connection_service.entity.PersonEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface PersonsRepository extends Neo4jRepository<PersonEntity, Long> {

    Optional<PersonEntity> getByName(String name);

    @Query("MATCH (personA:Person {userId: $userId}) " +
            "-[:CONNECTED_TO]-> " +
            "(personB:Person) " +
            "RETURN personB")
    List<PersonEntity> getFirstDegreeConnections(Long userId);

}
