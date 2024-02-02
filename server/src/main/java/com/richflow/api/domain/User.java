package com.richflow.api.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "t_users")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberIdx;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_password")
    private String memberPassword;

    @Column(name = "join_type")
    private String joinType;

    @Column(name = "member_sns_id")
    private String memberSnsId;

    @Column(name = "member_status")
    private String memberStatus;

    @Column(name = "agree_sms_status")
    private String agreeSmsStatus;

    @Column(name = "agree_email_status")
    private String agreeEmailStatus;

    @Column(name = "agree_sms_status_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date agreeSmsStatusUpdateDate;

    @Column(name = "agree_email_status_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date agreeEmailStatusUpdateDate;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "create_ip")
    private String createIp;

    @Column(name = "maintenance_period")
    @Temporal(TemporalType.TIMESTAMP)
    private Date maintenancePeriod;

    @Column(name = "maintenance_period_edit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date maintenancePeriodEditDate;

    @Column(name = "maintenance_period_edit_ip")
    private String maintenancePeriodEditIp;

    @Column(name = "leave_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveDate;

    @Column(name = "block_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date blockDate;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private Set<Accounting> accountings = new HashSet<>();

    // Getters and setters...
}
