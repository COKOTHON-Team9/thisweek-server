package com.susu.defaultserver.api.controller;

import com.susu.defaultserver.api.entity.Member;
import com.susu.defaultserver.api.service.MemberService;
import com.susu.defaultserver.api.service.dto.LoginRequestDto;
import com.susu.defaultserver.api.service.dto.SignupRequestDto;
import com.susu.defaultserver.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/member/login")
    public ApiResponse memberLogin(@RequestBody LoginRequestDto requestDto) {
        Member member = memberService.login(requestDto);

        if (member != null) return ApiResponse.success("member", member);
        else return ApiResponse.fail();
    }
}
