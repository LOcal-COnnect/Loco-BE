package com.likelion.loco.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.likelion.loco.global.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeIdx")
    private Long storeIdx;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "SellerIdx",nullable = true)
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "CategoryIdx", nullable = true)
    private Category category;

    @Column(name = "storeName", nullable = false)
    private String storeName;

    @Column(name = "storePhone", nullable = true)
    private String storePhone;

    @Column(name = "storeTel", nullable = true)
    private String storeTel;

    @Column(name = "storeDesc", nullable = true)
    private String storeDesc;

    @Column(name = "storeAddress", nullable = false)
    private String storeAddress;

    @Column(name = "storeDetailAddress", nullable = false)
    private String storeDetailAddress;

    @Column(name = "businessNumber", nullable = false)
    private String businessNumber;

}
