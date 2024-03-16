package com.richflow.api.request.user;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class UserRequest {

    private Long userIdx;
    private String userId;
    private String userPassword;
    private String userJoinType;
    private String userNickname;

    private String uslgUpdateIp;
}
