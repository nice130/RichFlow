package com.richflow.api.request.accountingTypes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CreateAccountingDTO {
    private Long actIdx;
    private Long userIdx;
    private Long actParentIdx;
    private String actEither;
    private String actCtgName;
    private int actSeq;
    private Date actCreateAt;
    private Date actUpdateAt;

    @Override
    public String toString() {
        return "CreateAccountingDTO{" +
                "actIdx=" + actIdx +
                ", userIdx=" + userIdx +
                ", actParentIdx=" + actParentIdx +
                ", actEither='" + actEither + '\'' +
                ", actCtgName='" + actCtgName + '\'' +
                ", actSeq=" + actSeq +
                ", actCreateAt=" + actCreateAt +
                ", actUpdateAt=" + actUpdateAt +
                '}';
    }
}
