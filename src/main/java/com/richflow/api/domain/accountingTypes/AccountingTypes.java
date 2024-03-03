package com.richflow.api.domain.accountingTypes;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "t_accounting_types")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountingTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "act_idx")
    private Long actIdx;

    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "act_parent_idx")
    private Long actParentIdx;

    @Enumerated(EnumType.STRING)
    @Column(name = "act_either")
    private ActEither actEither;

    @Column(name = "act_ctg_name")
    private String actCtgName;

    @Column(name = "act_seq")
    private int actSeq;

    @Column(name = "act_create_at")
    private Date actCreateAt;

    @Column(name = "act_update_at")
    private Date actUpdateAt;
}
