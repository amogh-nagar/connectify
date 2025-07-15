package com.connectify.user_service.dto;

import lombok.Data;

@Data
public class SignupReqDto {
    private String name;
    private String email;
    private String password;
}
