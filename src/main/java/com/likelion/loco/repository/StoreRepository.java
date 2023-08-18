package com.likelion.loco.repository;

import com.likelion.loco.entities.Seller;
import com.likelion.loco.entities.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    boolean existsBySeller(Seller seller);

    Page<Store> findByStoreNameContaining(String storeName, Pageable pageable);
    Page<Store> findByCategory(String category, Pageable pageable);


}
