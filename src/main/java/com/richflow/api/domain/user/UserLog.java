package com.richflow.api.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Entity
@Table(name = "t_users_log")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uslg_idx")
    private Long uslgIdx;

    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "uslg_update_ip")
    private String uslgUpdateIp;

    @Column(name = "uslg_update_at")
    private Date uslgUpdateAt;
}
