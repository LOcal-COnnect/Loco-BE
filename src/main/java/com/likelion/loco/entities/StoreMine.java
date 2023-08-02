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
public class StoreMine extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeMineIdx")
    private Long storeMineIdx;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userIdx")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "storeIdx")
    private Store store;
}
