package com.susu.defaultserver.api.service.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    public String email;
    public String password;
}
