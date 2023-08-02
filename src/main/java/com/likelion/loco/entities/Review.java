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

    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;

    @Column(name="reviewContent", nullable = false)
    private String reviewContent;

    @Column(name="reviewStar", nullable = false)
    private Integer reviewStar;





}
