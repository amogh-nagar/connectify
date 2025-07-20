package com.connectify.connection_service.repository;

import com.connectify.connection_service.entity.PersonEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface PersonsRepository extends Neo4jRepository<PersonEntity, Long> {

    Optional<PersonEntity> getByName(String name);

    @Query("MATCH (personA:Person {userId: $userId})" +
            "-[:CONNECTED_TO]->" +
            "(personB:Person) " +
            "RETURN personB")
    List<PersonEntity> getFirstDegreeConnections(Long userId);


    @Query("MATCH (personA:Person {userId: $userAId}) " +
            "MATCH (personB:Person {userId: $userBId}) " +
            "MERGE (personA)-[:CONNECTED_TO]->(personB) " +
            "RETURN personA,personB"
    )
    List<PersonEntity> createConnection(Long userAId, Long userBId);

    @Query("MERGE (personA:Person {userId: $userId}) " +
            "SET personA.name=$name " +
            "RETURN personA")
    PersonEntity createPerson(Long userId, String name);
}
