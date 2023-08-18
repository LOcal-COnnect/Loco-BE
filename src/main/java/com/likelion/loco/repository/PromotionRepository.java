package com.likelion.loco.repository;

import com.likelion.loco.entities.Promotion;
import com.likelion.loco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;




import com.likelion.loco.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
  
    Optional<Promotion> findPromotionByPromotionIdx(Long promotionIdx);

    Promotion findByPromotionIdx(Long promotionIdx);

    List<Promotion> findAllBySeller(Seller seller);
}
