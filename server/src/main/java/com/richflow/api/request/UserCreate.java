package com.convenientBook.api.request;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class UserCreate {

    private String memberId;
    private String memberPassword;

}
