package com.susu.defaultserver.api.service.dto;

import lombok.Data;

@Data
public class SignupRequestDto {

    public String email;

    public String nickname;

    public String password;

    public String profile;
}
