package com.susu.defaultserver.api.service;

import com.susu.defaultserver.api.entity.*;
import com.susu.defaultserver.api.repository.CommentRepository;
import com.susu.defaultserver.api.repository.MemberRepository;
import com.susu.defaultserver.api.repository.HeartRepository;
import com.susu.defaultserver.api.repository.PostRepository;
import com.susu.defaultserver.api.service.dto.CommentRequestDto;
import com.susu.defaultserver.api.service.dto.HeartRequestDto;
import com.susu.defaultserver.api.service.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;

    /* 글 작성 */
    @Transactional
    public Post write(PostRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow();
        Post post = Post.builder()
                .member(member)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
        Long id = postRepository.saveAndFlush(post).getId();

        return postRepository.findById(id).orElseThrow();
    }

    /* 글 삭제 */
    @Transactional
    public void deletePost(Long postId) {
        postRepository.delete(postRepository.findById(postId).orElseThrow());
    }

    /* 댓글 작성 */
    @Transactional
    public Comment writeComment(CommentRequestDto requestDto) {
        Comment comment = Comment.builder() // 여기 나중에 예외처리 필요
                .content(requestDto.getContent())
                .member(memberRepository.findById(requestDto.getMemberId()).orElseThrow())
                .post(postRepository.findById(requestDto.getPostId()).orElseThrow())
                .build();

        postRepository.findById(requestDto.getPostId()).orElseThrow().increaseCommentCount(); // 댓글 수 증가
        Long commentId = commentRepository.save(comment).getId();
        return commentRepository.findById(commentId).orElseThrow();
    }

    /* 댓글 삭제 */
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        postRepository.findById(commentId).orElseThrow().decreaseCommentCount(); // 댓글 수 감소
        commentRepository.delete(comment);
    }

    /* 좋아요 처리 */
    @Transactional
    public void heart(HeartRequestDto requestDto) {
        // 이미 좋아요 된 상태면
        if (heartRepository.findHeartByMemberIdAndPostId(requestDto.getMemberId(), requestDto.getPostId()).isPresent()) {
            unheart(requestDto);
        }
        else {
            postRepository.findById(requestDto.getPostId()).orElseThrow().increaseLikeCount(); // 좋아요 수 증가
            Heart heart = Heart.builder()
                    .member(memberRepository.findById(requestDto.getMemberId()).orElseThrow())
                    .post(postRepository.findById(requestDto.getPostId()).orElseThrow())
                    .build();
            heartRepository.saveAndFlush(heart);
        }
    }

    @Transactional
    public void unheart(HeartRequestDto requestDto) {
        Heart heart = heartRepository.findHeartByMemberIdAndPostId(requestDto.getMemberId(), requestDto.getPostId()).orElseThrow();
        postRepository.findById(requestDto.getPostId()).orElseThrow().decreaseLikeCount();
        heartRepository.delete(heart);
    }

    /* 게시글 전체 조회 - 최신순 */
    @Transactional
    public List<Post> readAllPosts() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
        return posts;
    }
}
