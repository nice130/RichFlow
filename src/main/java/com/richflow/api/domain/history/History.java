package com.richflow.api.domain.history;

import com.richflow.api.converter.MoneyTypeConverter;
import com.richflow.api.domain.accountingTypes.AccountingTypes;
import com.richflow.api.domain.accounts.Accounts;
import com.richflow.api.domain.enumType.ActEither;
import com.richflow.api.domain.enumType.AcMoneyType;
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

    private Long userIdx;

    @Enumerated(EnumType.STRING)
    @Column(name = "history_act_either")
    private ActEither historyActEither;

    @Enumerated(EnumType.STRING)
    @Column(name = "history_ac_money_type")
    private AcMoneyType historyAcMoneyType;

    private String historyName;

//    private BigDecimal historyAmounts;

    private String historyMemo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date historyCreateAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date historyUpdateAt;

    @ManyToOne
    @JoinColumn(name = "act_idx")
    private AccountingTypes accountingTypes;

    @ManyToOne
    @JoinColumn(name = "ac_idx")
    private Accounts accounts;

    @Builder
    public History(ActEither historyActEither, AcMoneyType historyAcMoneyType,
                   String historyName, BigDecimal historyAmounts, String historyMemo) {
        this.historyActEither = historyActEither;
        this.historyAcMoneyType = historyAcMoneyType;
        this.historyName = historyName;
//        this.historyAmounts = historyAmounts;
        this.historyMemo = historyMemo;
        this.historyCreateAt = new Date();
        this.historyUpdateAt = new Date();
    }

    public void updateDetails(UpdateHistoryDTO updateHistoryDTO) {
        this.historyActEither = updateHistoryDTO.getHistoryActEither();
        this.historyAcMoneyType = updateHistoryDTO.getHistoryAcMoneyType();
        this.historyName = updateHistoryDTO.getHistoryName();
//        this.historyAmounts = updateHistoryDTO.getHistoryAmounts();
        this.historyMemo = updateHistoryDTO.getHistoryMemo();
        this.historyUpdateAt = new Date(); // Set update timestamp
    }
}
