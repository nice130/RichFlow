package com.richflow.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private int code;
    private String message;
    private Object data;
}
