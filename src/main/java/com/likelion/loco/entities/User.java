package com.likelion.loco.entities;

import com.likelion.loco.global.BaseEntity;
import com.likelion.loco.global.enums.RoleType;
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

    @Column(name="userId", nullable = false)
    private String userId;

    @Column(name="userName",nullable = false)
    private String userName;

    @Column(name="userPassword",nullable = false)
    private String userPassword;

    @Column(name = "userEmail",nullable = false)
    private String userEmail;

    @Column(name="userPhone",nullable = false)
    private String userPhone;

    @Column(name="userAddress",nullable = false)
    private String userAddress;

    @Column(name = "userDetailAddress",nullable = false)
    private String userDetailAddress;


    @Enumerated(EnumType.STRING)
    private RoleType roleType;


}
