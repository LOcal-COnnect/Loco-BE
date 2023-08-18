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
public class Good extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodIdx;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userIdx")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="promotionIdx")
    private Promotion promotion;

}

