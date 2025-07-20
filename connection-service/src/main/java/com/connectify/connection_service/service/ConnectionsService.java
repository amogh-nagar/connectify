package com.connectify.connection_service.service;

import com.connectify.connection_service.context.UserContextHolder;
import com.connectify.connection_service.dto.PersonDto;
import com.connectify.connection_service.dto.UserDto;
import com.connectify.connection_service.entity.PersonEntity;
import com.connectify.connection_service.repository.PersonsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ConnectionsService {

    private final PersonsRepository personsRepository;
    private final ModelMapper modelMapper;

    public List<PersonDto> getFirstDegreeConnections() {
        UserDto user = UserContextHolder.getCurrentUser();
        List<PersonEntity> persons = personsRepository.getFirstDegreeConnections(user.getId());
        return persons.stream()
                .map(personEntity -> modelMapper.map(personEntity, PersonDto.class))
                .collect(Collectors.toList());
    }

    public List<PersonDto> createConnection(Long userId) {
        UserDto userA = UserContextHolder.getCurrentUser();
        List<PersonEntity> persons = personsRepository.createConnection(userA.getId(), userId);
        return persons.stream()
                .map(personEntity -> modelMapper.map(personEntity, PersonDto.class))
                .collect(Collectors.toList());
    }

    public PersonDto createPerson(Long userId, String name) {
        PersonEntity personEntity = personsRepository.createPerson(userId, name);
        return modelMapper.map(personEntity, PersonDto.class);
    }

}
