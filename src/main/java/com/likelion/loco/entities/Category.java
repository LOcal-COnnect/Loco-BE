package com.likelion.loco.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryIdx")
    private Long categoryIdx;

    @Column(name = "CategoryName", nullable = false)
    private String categoryName;
}
