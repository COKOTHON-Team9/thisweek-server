package com.susu.defaultserver.api.service.dto;

import lombok.Data;

@Data
public class PostRequestDto {

    public String title;
    public String content;
    public Long memberId; // memberId 요청으로 받아서 Member 객체 찾아서 조인해서 넘기기
}
