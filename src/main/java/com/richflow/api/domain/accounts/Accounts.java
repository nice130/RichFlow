package com.richflow.api.domain.accounts;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "t_accounts")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ac_idx")
    private Long acIdx;

    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "ac_level")
    private int acLevel;

    @Column(name = "ac_parent_level")
    private int acParentLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "ac_money_type")
    private MoneyType acMoneyType;

    @Column(name = "ac_name")
    private String acName;

    @Column(name = "ac_seq")
    private int acSeq;

    @Column(name = "ac_create_at")
    private Date acCreateAt;

    @Column(name = "ac_update_at")
    private Date acUpdateAt;
}
