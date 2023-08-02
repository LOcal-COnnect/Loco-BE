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
public class Seller extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerIdx;

    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL)
    private Store store;

    @Column(name="sellerName", nullable = false)
    private String sellerName;

    @Column(name="sellerPassword",nullable = false)
    private String sellerPassword;

    @Column(name = "sellerEmail",nullable = false)
    private String sellerEmail;

    @Column(name="sellerPhone",nullable = false)
    private String sellerPhone;

    @Column(name="sellerAddress",nullable = false)
    private String sellerAddress;

    @Column(name = "sellerDetailAddress",nullable = false)
    private String sellerDetailAddress;




}
