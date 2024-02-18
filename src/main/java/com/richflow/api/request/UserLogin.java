package com.richflow.api.request;

import lombok.*;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
public class UserLogin {

    private Long userIdx;
    private String userId;
    private String userPassword;
    private String joinType;
    private String userNickname;
}
