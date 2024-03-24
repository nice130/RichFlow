package com.richflow.api.request.accounts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AccountsRequest {

    private Long userIdx;
    private String userId;

    private int acLevel;
    private int acParentLevel;
    private String acMoneyType;
    private Long acAmount;
    private String acName;
    private int acSeq;
}
