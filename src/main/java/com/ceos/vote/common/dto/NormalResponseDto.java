package com.ceos.vote.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NormalResponseDto {
    private String status;
    private String message;

    protected NormalResponseDto(String status) {
        this.status = status;
    }

    public static NormalResponseDto success() {
        return new NormalResponseDto("SUCCESS");
    }

    public static NormalResponseDto fail() {
        return new NormalResponseDto("FAIL");
    }

}