package com.likelion.loco.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.likelion.loco.global.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private Seller seller;

    @JsonIgnore
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    private List<Good> goods ;

    @Column(name = "promotionTitle", nullable = false)
    private String promotionTitle;

    @Column(name = "promotionContent", nullable = false)
    private String promotionContent;

    @Column(name = "viewCount", nullable = false)
    private Integer viewCount;

}
