package com.connectify.user_service.controller;

import com.connectify.user_service.dto.LoginReqDto;
import com.connectify.user_service.dto.SignupReqDto;
import com.connectify.user_service.dto.UserDto;
import com.connectify.user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupReqDto signupReqDto) {
        UserDto userDto = authService.signup(signupReqDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginReqDto loginReqDto) {
        String token = authService.login(loginReqDto);
        return ResponseEntity.ok(token);
    }

}
