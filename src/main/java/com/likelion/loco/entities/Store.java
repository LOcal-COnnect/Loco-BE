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

//    @OneToOne
//    @JoinColumn(name = "SellerIdx")
//    private Seller seller;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="userIdx")
    private User user;

//    @ManyToOne(optional = false)
//    @JoinColumn(name = "CategoryIdx")
//    private Category category;

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

    @Column(name = "businessNumber", nullable = false)
    private Integer businessNumber;

}
