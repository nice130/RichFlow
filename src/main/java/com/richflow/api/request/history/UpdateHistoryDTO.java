package com.richflow.api.request.history;

import com.richflow.api.domain.enumType.ActEither;
import com.richflow.api.domain.enumType.AcMoneyType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class UpdateHistoryDTO {

    private Long historyIdx;
    private ActEither historyActEither;
    private AcMoneyType historyAcMoneyType;
    private String historyName;
    private BigDecimal historyAmounts;
    private String historyMemo;
}
