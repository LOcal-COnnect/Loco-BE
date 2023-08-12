package com.likelion.loco.repository;

import com.likelion.loco.entities.Promotion;
import com.likelion.loco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PromotionRepository extends JpaRepository<Promotion,Long> {
    Optional<Promotion> findPromotionByPromotionIdx(Long promotionIdx);
}
