package com.ssafy.B310.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {
    private String ACCESS_TOKEN;
    private String REFRESH_TOKEN;
}