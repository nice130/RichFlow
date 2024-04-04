package com.richflow.api.request.accountingTypes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class UpdateAccountingDTO {
    private Long actidx;
    private Long useridx;
    private Long actparentidx;
    private String actEither;
    private String actctgname;
    private int actseq;
    private Date actupdateat;

    @Override
    public String toString() {
        return "UpdateAccountingDTO{" +
                "actidx=" + actidx +
                ", useridx=" + useridx +
                ", actparentidx=" + actparentidx +
                ", actEither='" + actEither + '\'' +
                ", actctgname='" + actctgname + '\'' +
                ", actseq=" + actseq +
                ", actupdateat=" + actupdateat +
                '}';
    }
}
