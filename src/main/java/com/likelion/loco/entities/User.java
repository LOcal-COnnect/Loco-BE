package com.likelion.loco.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.likelion.loco.global.BaseEntity;
import com.likelion.loco.global.enums.RoleType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>(); // 빈 리스트로 초기화

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

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;


}
