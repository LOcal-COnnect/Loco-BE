package com.likelion.loco.repository;

import com.likelion.loco.entities.Promotion;
import com.likelion.loco.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Promotion findByPromotionIdx(Long promotionIdx);

    List<Promotion> findAllBySeller(Seller seller);
}
