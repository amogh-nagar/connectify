package com.connectify.connection_service.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Person")
@Data
public class PersonEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String name;

}
