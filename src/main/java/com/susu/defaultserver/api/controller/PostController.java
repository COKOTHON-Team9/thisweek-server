package com.susu.defaultserver.api.controller;

import com.susu.defaultserver.api.entity.Post;
import com.susu.defaultserver.api.service.PostService;
import com.susu.defaultserver.api.service.dto.CommentRequestDto;
import com.susu.defaultserver.api.service.dto.HeartRequestDto;
import com.susu.defaultserver.api.service.dto.PostRequestDto;
import com.susu.defaultserver.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

    @PostMapping("/post") // 게시글 작성
    public ApiResponse writePost(@RequestBody PostRequestDto requestDto) {
        Post post = postService.write(requestDto);
        return ApiResponse.success("post", post);
    }

    @DeleteMapping("/post/delete") // 게시글 삭제
    public ApiResponse deletePost(@RequestParam Long id) {
        postService.deletePost(id);
        return ApiResponse.success("result", "삭제 완료 - 삭제된 글 id : " + id);
    }

    @PostMapping("/comment") // 댓글 작성
    public ApiResponse writeComment(@RequestBody CommentRequestDto requestDto) {
        return ApiResponse.success("comment", postService.writeComment(requestDto));
    }

    @DeleteMapping("/comment/delete") // 댓글 삭제
    public ApiResponse deleteComment(@RequestParam Long id) {
        postService.deleteComment(id);
        return ApiResponse.success("result", "댓글 삭제 완료 - 삭제된 댓글 id : " + id);
    }

    @PutMapping("/heart") // 좋아요 or 좋아요 취소 동시 처리
    public ApiResponse pressHeart(@RequestBody HeartRequestDto requestDto) {
        postService.heart(requestDto);
        return ApiResponse.success("result", "좋아요/취소 처리 완료");
    }

    @GetMapping("/readall/newest")
    public ApiResponse readAllOrderByNewest() {
        return ApiResponse.success("result", postService.readAllPosts());
    }

}
