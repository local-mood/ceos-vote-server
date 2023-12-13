package com.ceos.vote.auth.controller;

import com.ceos.vote.auth.service.AuthService;
import com.ceos.vote.common.dto.NormalResponseDto;
import com.ceos.vote.auth.jwt.entity.TokenDto;
import com.ceos.vote.auth.jwt.entity.LoginRequestDto;
import com.ceos.vote.domain.member.dto.MemberRequestDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/auth")
@RequiredArgsConstructor
public class AuthController {

    private final long COOKIE_EXPIRATION = 7776000; // 90일

    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<NormalResponseDto> join(@RequestBody @Valid MemberRequestDto requestDto) {
        authService.joinMember(requestDto);
        return ResponseEntity.ok(NormalResponseDto.success());
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {

        TokenDto tokenDto = authService.login(loginRequest);

        HttpCookie httpCookie = ResponseCookie.from("refresh-token", tokenDto.getRefreshToken())
                .maxAge(COOKIE_EXPIRATION)
                .httpOnly(true)
                .secure(true)
                .sameSite(Cookie.SameSite.NONE.attributeValue())    //서드파티 쿠키 사용 허용
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, httpCookie.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenDto.getAccessToken())
                .build();
    }


}