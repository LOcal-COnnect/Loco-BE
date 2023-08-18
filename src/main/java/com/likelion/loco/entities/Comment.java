package com.likelion.loco.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.likelion.loco.global.BaseEntity;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userIdx")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="sellerIdx")
    private Seller seller;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="promotionIdx")
    private Promotion promotion;
  
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
  
