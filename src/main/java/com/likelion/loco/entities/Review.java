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
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewIdx;

    @Column(name="review_content")
    private String reviewContent;

    @Column(name="review_star")
    private Integer reviewStar;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
