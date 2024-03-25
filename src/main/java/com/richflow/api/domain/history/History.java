package com.richflow.api.domain.history;

import com.richflow.api.converter.MoneyTypeConverter;
import com.richflow.api.domain.enumType.ActEither;
import com.richflow.api.domain.enumType.MoneyType;
import com.richflow.api.request.history.UpdateHistoryDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Entity
@Table(name = "t_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyIdx;

    private Long acIdx;

    private Long userIdx;

    private Long actIdx;

    @Enumerated(EnumType.STRING)
    private ActEither historyActEither;

    @Convert(converter = MoneyTypeConverter.class)
    private MoneyType historyAcMoneyType;

    private String historyName;

    private BigDecimal historyAmounts;

    private String historyMemo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date historyCreateAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date historyUpdateAt;

    @Builder
    public History(ActEither historyActEither, MoneyType historyAcMoneyType,
                   String historyName, BigDecimal historyAmounts, String historyMemo) {
        this.historyActEither = historyActEither;
        this.historyAcMoneyType = historyAcMoneyType;
        this.historyName = historyName;
        this.historyAmounts = historyAmounts;
        this.historyMemo = historyMemo;
        this.historyCreateAt = new Date();
        this.historyUpdateAt = new Date();
    }

    public void updateDetails(UpdateHistoryDTO updateHistoryDTO) {
        this.historyActEither = updateHistoryDTO.getHistoryActEither();
        this.historyAcMoneyType = updateHistoryDTO.getHistoryAcMoneyType();
        this.historyName = updateHistoryDTO.getHistoryName();
        this.historyAmounts = updateHistoryDTO.getHistoryAmounts();
        this.historyMemo = updateHistoryDTO.getHistoryMemo();
        this.historyUpdateAt = new Date(); // Set update timestamp
    }
}
