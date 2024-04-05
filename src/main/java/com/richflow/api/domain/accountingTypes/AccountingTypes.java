package com.richflow.api.domain.accountingTypes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.richflow.api.domain.enumType.ActEither;
import com.richflow.api.request.accountingTypes.UpdateAccountingDTO;
import jakarta.persistence.*;
import lombok.*;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "act_create_at")
    private Date actCreateAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "act_update_at")
    private Date actUpdateAt;

    /*
        카테고리 생성 빌더
        첫 생성시 업데이트일자 생성일자로 넣어줌
        분류키(기본인덱스),회원인덱스,부모인덱스,수입/지출 순번 추가예정
    */
    @Builder
    public AccountingTypes(ActEither actEither, String actCtgName, int actSeq) {
        this.actEither = actEither;
        this.actCtgName = actCtgName;
        this.actSeq = actSeq;
        this.actCreateAt = new Date();
        this.actUpdateAt = new Date();
    }

    public void updateCategory(UpdateAccountingDTO updateDTO) {
        this.actIdx = updateDTO.getActidx();
        this.actEither = ActEither.valueOf(updateDTO.getActEither());
        this.actCtgName = updateDTO.getActctgname();
        this.actUpdateAt = new Date();
    }
}