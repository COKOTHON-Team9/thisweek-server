package com.susu.defaultserver.api.service.dto;

import com.susu.defaultserver.api.entity.Member;
import com.susu.defaultserver.api.entity.Post;
import lombok.Data;

/* 최초 좋아요 요청시 */
@Data
public class HeartRequestDto {

    public Long memberId;
    public Long postId;
}
