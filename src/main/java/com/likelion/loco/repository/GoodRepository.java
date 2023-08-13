package com.likelion.loco.repository;

import com.likelion.loco.entities.Good;
import com.likelion.loco.entities.Promotion;
import com.likelion.loco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface GoodRepository extends JpaRepository<Good,Long> {
    @Query("SELECT COUNT(g) FROM Good g WHERE g.promotion = :promotion")
    Integer countByPromotion(@Param("promotion") Promotion promotion);

    Optional<Good> findGoodByUserAndPromotion(User user, Promotion promotion);

    void deleteAllByUserAndPromotion(User user, Promotion promotion);

}
