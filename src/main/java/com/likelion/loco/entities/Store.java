package com.likelion.loco.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.likelion.loco.global.BaseEntity;
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
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeIdx")
    private Long storeIdx;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "sellerIdx")
    private Seller seller;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>(); // 빈 리스트로 초기화

    @Column(name = "storeName", nullable = false)
    private String storeName;

    @Column(name = "storePhone", nullable = false)
    private String storePhone;

    @Column(name = "storeTel", nullable = false)
    private String storeTel;

    @Column(name = "storeDesc", nullable = false)
    private String storeDesc;

    @Column(name = "storeLocation", nullable = false)
    private String storeLocation;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "businessNumber", nullable = true)
    private String businessNumber;

}
