package com.richflow.api.domain.user;

import com.richflow.api.domain.accounts.Accounts;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "t_users")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_join_type")
    private String userJoinType;

    @Column(name = "user_sns_id")
    private String userSnsId;

    @Column(name = "user_status")
    private String userStatus;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_create_at")
    private Date userCreateAt;

    @OneToMany
    private List<Accounts> accounts;
}
