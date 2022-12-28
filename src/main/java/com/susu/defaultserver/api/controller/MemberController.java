package com.susu.defaultserver.api.controller;

import com.susu.defaultserver.api.entity.Member;
import com.susu.defaultserver.api.service.MemberService;
import com.susu.defaultserver.api.service.dto.SignupRequestDto;
import com.susu.defaultserver.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/signup") // 회원가입 처리
    public ApiResponse memberSignup(@RequestBody SignupRequestDto requestDto) {

        Member member = memberService.signup(requestDto);

        return ApiResponse.success("result", member);
    }
}
