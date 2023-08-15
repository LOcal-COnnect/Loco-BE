package com.likelion.loco.repository;

import com.likelion.loco.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller,Long> {
    Optional<Seller> findBySellerId(String sellerId);
    boolean existsBySellerId(String sellerId);

}
