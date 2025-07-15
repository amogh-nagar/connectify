package com.connectify.connection_service.service;

import com.connectify.connection_service.dto.PersonDto;
import com.connectify.connection_service.entity.PersonEntity;
import com.connectify.connection_service.repository.PersonsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ConnectionsService {

    private final PersonsRepository personsRepository;
    private final ModelMapper modelMapper;

    public List<PersonDto> getFirstDegreeConnections(Long userId) {
        List<PersonEntity> persons = personsRepository.getFirstDegreeConnections(userId);
        return persons.stream()
                .map(personEntity -> modelMapper.map(personEntity, PersonDto.class))
                .collect(Collectors.toList());
    }

}
