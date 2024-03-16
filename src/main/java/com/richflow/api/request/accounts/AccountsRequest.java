package com.richflow.api.request.accounts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AccountsRequest {
    private Long acIdx;
    private Long userIdx;
    private Long acLevel;
    private Long acParentLevel;

    private String acMoneyType;
    private String acName;
    private int acSeq;
    private Date acCreateAt;
    private Date acUpdateAt;
}
