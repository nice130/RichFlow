package com.richflow.api.request.accountingTypes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AccountingTypesRequest {
    private Long actIdx;
    private Long userIdx;
    private Long actParentIdx;
    private String actEither;
    private String actCtgName;
    private int actSeq;
    private Date actCreateAt;
    private Date actUpdateAt;
}
