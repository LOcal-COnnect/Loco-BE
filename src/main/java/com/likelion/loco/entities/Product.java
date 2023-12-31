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
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productIdx")
    private Long productIdx;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "storeIdx")
    private Store store;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productPrice", nullable = false)
    private Integer productPrice;
}
