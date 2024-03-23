package com.richflow.api.response.accounts;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountsResponse {
    private int code;
    private String message;
    private Object data;
}
