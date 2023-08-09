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
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentIdx;

    @Column(name="commentContent", nullable = false)
    private String commentContent;

    @ManyToOne
    @JoinColumn(name="userIdx")
    private User user;

    @ManyToOne
    @JoinColumn(name="promotionIdx")
    private Promotion promotion;
}
