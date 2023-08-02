package com.likelion.loco.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subCategoryIdx")
    private Long subCategoryIdx;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mainCategory")
    private MainCategory mainCategory;

    @Column(name = "subCategoryName", nullable = false)
    private String subCategoryName;
}
