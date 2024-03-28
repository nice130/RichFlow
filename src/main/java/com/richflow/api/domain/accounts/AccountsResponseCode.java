package com.richflow.api.domain.accounts;

public class AccountsResponseCode {
    public static final int SUCCESS = 200;

    public static final int FAIL = 501;

    public static String getMessage(int code) {
        switch (code) {
            case SUCCESS:
                return "성공";
            case FAIL:
                return "실패";
            default:
                return "알 수 없는 에러. 관리자에게 문의하세요";
        }
    }
}