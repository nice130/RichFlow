package com.richflow.api.request.accounts;

import com.richflow.api.domain.enumType.AcMoneyType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateAccountsDTO {
    private Long acIdx;
    private int acLevel;
    private Long acParentIdx;
    private AcMoneyType acMoneyType;
    private Long acAmount;
    private String acName;
    private int acSeq;
}
