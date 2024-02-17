package com.richflow.api.request;

import lombok.*;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
public class UserLogin {

    private Long memberIdx;
    private String memberId;
    private String memberPassword;
    private String joinType;
    private String memberNickname;
}
