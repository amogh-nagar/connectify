package com.connectify.user_service.dto;

import lombok.Data;

@Data
public class LoginReqDto {
    private String email;
    private String password;
}
