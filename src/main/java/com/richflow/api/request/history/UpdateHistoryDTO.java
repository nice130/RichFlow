package com.richflow.api.request.history;

import com.richflow.api.domain.enumType.ActEither;
import com.richflow.api.domain.enumType.MoneyType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class UpdateHistoryDTO {

    private Long historyIdx;
    private ActEither historyActEither;
    private MoneyType historyAcMoneyType;
    private String historyName;
    private BigDecimal historyAmounts;
    private String historyMemo;
}
