package com.connectify.connection_service.controller;

import com.connectify.connection_service.dto.PersonDto;
import com.connectify.connection_service.dto.UserDto;
import com.connectify.connection_service.exception.BadRequestException;
import com.connectify.connection_service.service.ConnectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    @GetMapping("/first-degree")
    private ResponseEntity<List<PersonDto>> getFirstDegreeConnections() {
        return ResponseEntity.ok(connectionsService.getFirstDegreeConnections());
    }

    @PostMapping("/create-connection/{userId}")
    private ResponseEntity<List<PersonDto>> createConnection(@PathVariable Long userId) {
        List<PersonDto> personDtos = connectionsService.createConnection(userId);
        if(personDtos.isEmpty()) {
            throw new BadRequestException("Bad request, userB not found with id : " + userId);
        }
        return ResponseEntity.ok(personDtos);
    }

    @PostMapping("/create-person")
    private ResponseEntity<PersonDto> createPerson(@RequestBody UserDto userDto) {
        PersonDto personDto = connectionsService.createPerson(userDto.getId(), userDto.getName());
        return ResponseEntity.ok(personDto);
    }
}
