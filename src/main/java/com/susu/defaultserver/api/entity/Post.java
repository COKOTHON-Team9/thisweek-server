package com.susu.defaultserver.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 글 식별번호

    @Column
    private String title; // 글 제목

    @Column
    private String content; // 글 내용

    @Column(name = "post_hits")
    private int hits;

    // 작성자 정보 조인용
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member author;

    // 게시글에 달린 댓글 정보 양방향 매핑
    @OneToMany(mappedBy = "originalPost", cascade = CascadeType.PERSIST) // 영속성 하위 전파
    @JsonIgnore
    private List<Comment> comment;

    // 게시글 작성용
    @Builder
    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.author = member;
    }
}
