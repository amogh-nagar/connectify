package com.connectify.user_service.service;

import com.connectify.user_service.dto.LoginReqDto;
import com.connectify.user_service.dto.SignupReqDto;
import com.connectify.user_service.dto.UserDto;
import com.connectify.user_service.entity.UserEntity;
import com.connectify.user_service.exception.BadRequestException;
import com.connectify.user_service.exception.ResourceNotFoundException;
import com.connectify.user_service.repository.UserRepository;
import com.connectify.user_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signup(SignupReqDto signupReqDto) {
        boolean userExists = userRepository.existsByEmail(signupReqDto.getEmail());
        if(userExists)
            throw new BadRequestException("User already exists");

        UserEntity user = modelMapper.map(signupReqDto, UserEntity.class);
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        UserEntity savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public String login(LoginReqDto loginReqDto) {
        UserEntity user = userRepository.findByEmail(loginReqDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + loginReqDto.getEmail()));
        boolean isPasswordMatch = PasswordUtil.comparePassword(loginReqDto.getPassword(), user.getPassword());
        if(!isPasswordMatch) {
            throw new BadRequestException("Incorrect password");
        }
        return jwtService.generateAccessToken(user);
    }
}
