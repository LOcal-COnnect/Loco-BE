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
public class Promotion extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionIdx;

    @ManyToOne
    @JoinColumn(name = "sellerIdx")
    private User seller;

    @Column(name = "promotionTitle", nullable = false)
    private String promotionTitle;

    @Column(name = "postContent", nullable = false)
    private String promotionContent;

}
