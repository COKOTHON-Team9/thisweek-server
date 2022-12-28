package com.susu.defaultserver.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id") // 댓글 작성자 정보, 단방향
    @JsonIgnore
    private Member author;

    @ManyToOne
    @JoinColumn(name = "post_id") // 원본 게시글 정보, 양방향
    @JsonIgnore
    private Post originalPost;

    @Builder
    public Comment(String content, Member member, Post originalPost) {
        this.content = content;
        this.author = member;
        this.originalPost = originalPost;
    }
}
