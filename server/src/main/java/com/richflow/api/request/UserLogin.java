package com.richflow.api.request;

import lombok.*;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
public class UserLogin {

    private String memberId;
    private String memberPassword;
    private String joinType;
    private String agreeSmsStatus;
    private String agreeEmailStatus;
    private String createIp;
    private String memberNickname;

}
