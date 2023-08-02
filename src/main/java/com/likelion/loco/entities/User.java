package com.likelion.loco.entities;

import com.likelion.loco.global.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_password")
    private String userPassword;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name="role")
    private Enum role;

    @Column(name="user_phone")
    private String userPhone;

    @Column(name="user_address")
    private String userAddress;

    @Column(name = "user_detail_address")
    private String userDetailAddress;




}
