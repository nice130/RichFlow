package com.richflow.api.request.accounts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AccountsRequest {

    private String userId;

    private Long acLevel;
    private Long acParentLevel;
    private String acMoneyType;
    private String acName;
    private int acSeq;
}
