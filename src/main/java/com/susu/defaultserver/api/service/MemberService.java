package com.susu.defaultserver.api.service;

import com.susu.defaultserver.api.entity.Member;
import com.susu.defaultserver.api.repository.MemberRepository;
import com.susu.defaultserver.api.service.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /* 회원가입 */
    @Transactional
    public Member signup(SignupRequestDto requestDto) {
        Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .profile(requestDto.getProfile())
                .nickname(requestDto.getNickname())
                .build();

        Long id = memberRepository.save(member).getId();
        return memberRepository.findById(id).orElseThrow();
    }

    /* 닉네임 중복검사 */
    @Transactional
    public boolean checkNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }
}
