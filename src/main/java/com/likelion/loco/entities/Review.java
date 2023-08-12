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
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewIdx;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "storeIdx")
    private Store store;

    @Column(name="reviewContent", nullable = false)
    private String reviewContent;

    @Column(name="reviewStar", nullable = false)
    private Integer reviewStar;

}
