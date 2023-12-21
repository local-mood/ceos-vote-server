package com.ceos.vote.common.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저 정보를 찾지 못했습니다.", "email 과 password 를 올바르게 입력했는지 확인해주세요"),
    ALREADY_MEMBER_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 유저 정보입니다.", "다른 이메일로 가입해주세요."),
    ALREADY_MEMBER_ID(HttpStatus.CONFLICT, "이미 존재하는 유저 정보입니다.", "다른 ID로 가입해주세요."),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "아이디 혹은 비밀번호가 틀렸습니다.", "다시 시도해주세요."),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 액세스 토큰입니다.", "다시 로그인해주세요."),
    ALREADY_VOTED_USER(HttpStatus.BAD_REQUEST, "이미 투표를 완료한 유저입니다.", "투표가 불가합니다.");

    private final HttpStatus httpStatus;
    private final String message;
    private final String solution;

}