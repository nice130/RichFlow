package com.richflow.api.domain.accounts;

import com.richflow.api.domain.enumType.AcMoneyType;
import com.richflow.api.request.accounts.UpdateAccountsDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Entity
@Table(name = "t_accounts")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ac_idx")
    private Long acIdx;

    private Long userIdx;

    private int acLevel;

    private Long acParentIdx;

    @Enumerated(EnumType.STRING)
    @Column(name = "ac_money_type")
    private AcMoneyType acMoneyType;

    private Long acAmount;

    private String acName;

    private int acSeq;

    @Temporal(TemporalType.TIMESTAMP)
    private Date acCreateAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date acUpdateAt;

    @Builder
    public Accounts(Long userIdx, int acLevel, Long acParentIdx, AcMoneyType acMoneyType,
                    Long acAmount, String acName, int acSeq) {
        this.userIdx = userIdx;
        this.acLevel = acLevel;
        this.acParentIdx = acParentIdx;
        this.acMoneyType = acMoneyType;
        this.acAmount = acAmount;
        this.acName = acName;
        this.acSeq = acSeq;
        this.acCreateAt = new Date();
    }

    public void updateAccounts(UpdateAccountsDTO updateAccountsDTO) {
        this.acIdx = updateAccountsDTO.getAcIdx();
        this.acLevel = updateAccountsDTO.getAcLevel();
        this.acParentIdx = updateAccountsDTO.getAcParentIdx();
        this.acMoneyType = updateAccountsDTO.getAcMoneyType();
        this.acAmount = updateAccountsDTO.getAcAmount();
        this.acName = updateAccountsDTO.getAcName();
        this.acSeq = updateAccountsDTO.getAcSeq();
        this.acUpdateAt = new Date();
    }

    public void updateAcAmount(Long acIdx, Long acAmount) {
        this.acIdx = acIdx;
        this.acAmount = acAmount;
        this.acUpdateAt = new Date();
    }
}
