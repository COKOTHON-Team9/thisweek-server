package com.susu.defaultserver.api.service.dto;

import lombok.Data;

@Data
public class CommentRequestDto {

    public String content;
    public Long memberId;
    public Long postId;
}
