package com.susu.defaultserver.api.controller;

import com.susu.defaultserver.api.entity.Post;
import com.susu.defaultserver.api.service.PostService;
import com.susu.defaultserver.api.service.dto.CommentRequestDto;
import com.susu.defaultserver.api.service.dto.PostRequestDto;
import com.susu.defaultserver.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ApiResponse writePost(@RequestBody PostRequestDto requestDto) {
        Post post = postService.write(requestDto);
        return ApiResponse.success("post", post);
    }

    @DeleteMapping("/delete")
    public ApiResponse deletePost(@RequestParam Long id) {
        postService.deletePost(id);
        return ApiResponse.success("msg", "삭제 완료 - 삭제된 글 id : " + id);
    }

    @PostMapping("/comment")
    public ApiResponse writeComment(@RequestBody CommentRequestDto requestDto) {
        return ApiResponse.success("comment", postService.writeComment(requestDto));
    }
}
