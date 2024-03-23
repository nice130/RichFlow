package com.richflow.api.domain.history;

import com.richflow.api.domain.enumType.ActEither;
import com.richflow.api.domain.enumType.AcMoneyType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "t_history")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_idx")
    private Long historyIdx;

    @Column(name = "ac_idx")
    private Long acIdx;

    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "act_idx")
    private Long actIdx;

    @Enumerated(EnumType.STRING)
    @Column(name = "history_act_either")
    private ActEither historyActEither;

    @Enumerated(EnumType.STRING)
    @Column(name = "history_ac_money_type")
    private AcMoneyType historyAcMoneyType;

    @Column(name = "history_name")
    private String historyName;

    @Column(name = "history_amounts")
    private String historyAmounts;

    @Column(name = "history_memo")
    private String historyMemo;

    @Column(name = "history_create_at")
    private Date historyCreateAt;

    @Column(name = "history_update_at")
    private Date historyUpdateAt;
}
