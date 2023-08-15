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
public class Seller extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerIdx;

    @Column(name="sellerId", nullable = false)
    private String sellerId;

    @Column(name="sellerName", nullable = false)
    private String sellerName;

    @Column(name="sellerPassword",nullable = false)
    private String sellerPassword;

    @Column(name = "sellerEmail",nullable = false)
    private String sellerEmail;

    @Column(name="sellerPhone",nullable = false)
    private String sellerPhone;

    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL)
    private Store store;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;




}
