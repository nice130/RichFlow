package com.richflow.api.domain.User;
public class UserCode {
    public static final int SUCCESS = 200;
    public static final int FAILURE_PASSWORD_CHK = 501;
    public static final int FAILURE_ID_CHK = 502;

    public static String getMessage(int code) {
        switch (code) {
            case SUCCESS:
                return "성공";
            case FAILURE_PASSWORD_CHK:
                return "비밀번호를 확인하세요";
            case FAILURE_ID_CHK:
                return "아이디를 확인하세요";
            default:
                return "알 수 없는 에러. 관리자에게 문의하세요";
        }
    }
}
