package com.susu.defaultserver.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 글 식별용

    @Column
    private String title; // 글 제목

    @Column
    private String content; // 글 내용

    @Column(name = "post_hits")
    private int hits;

    @Column
    private int likeCount; // 좋아요 수

    @Column
    private int commentCount; // 댓글 수

    // 작성자 정보 조인용
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member author;

    // 게시글에 달린 댓글 정보 양방향 매핑용
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Heart> heartList = new ArrayList<>();

    // 게시글 작성용
    @Builder
    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.author = member;
        this.likeCount = 0;
        this.commentCount = 0;
    }

    // 좋아요 수 조정
    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void decreaseLikeCount() {
        this.likeCount--;
    }

    // 댓글 수 조정
    public void increaseCommentCount() {
        this.commentCount++;
    }

    public void decreaseCommentCount() {
        this.commentCount--;
    }
}
