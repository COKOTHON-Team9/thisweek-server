package com.susu.defaultserver.api.service;

import com.susu.defaultserver.api.entity.Comment;
import com.susu.defaultserver.api.entity.Member;
import com.susu.defaultserver.api.entity.Post;
import com.susu.defaultserver.api.repository.CommentRepository;
import com.susu.defaultserver.api.repository.MemberRepository;
import com.susu.defaultserver.api.repository.PostLikeRepository;
import com.susu.defaultserver.api.repository.PostRepository;
import com.susu.defaultserver.api.service.dto.CommentRequestDto;
import com.susu.defaultserver.api.service.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;
    private final MemberRepository memberRepository;

    /*
    글 작성
    Request : requestDto
    Response : post 객체
     */
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

    /*
    글 삭제
    Request : 삭제할 글의 postId
    Response : 삭제 완료 msg
    */
    public void deletePost(Long postId) {
        postRepository.delete(postRepository.findById(postId).orElseThrow());
    }

    /*
    댓글 작성
    Request : 댓글 내용, 작성자 id, 원본 게시글 id
    Response : 작성된 Comment 객체
    */
    public Comment writeComment(CommentRequestDto requestDto) {
        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .member(memberRepository.findById(requestDto.getAuthorId()).orElseThrow())
                .originalPost(postRepository.findById(requestDto.getPostId()).orElseThrow())
                .build();

        Long commentId = commentRepository.save(comment).getId();
        return commentRepository.findById(commentId).orElseThrow();
    }

    /*
    좋아요 버튼 클릭
    */
}
