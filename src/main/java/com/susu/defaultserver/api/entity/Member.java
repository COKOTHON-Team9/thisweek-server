package com.susu.defaultserver.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "profile")
    private String profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "nickname")
    private String nickname;

    // 작성 글 조회용 (posts 와 매핑)
    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Post> posts;

    // 회원가입용 builder
    @Builder
    public Member(String email, String password, String profile, String nickname) {
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.role = Role.MEMBER;
        this.nickname = nickname;
    }
}
