package com.likelion.loco.repository;

import com.likelion.loco.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
//    @Query(value = "SELECT count(likes) from Like likes JOIN likes.promotion s where s.promotionIdx = :id")
//    Integer countById(@Param("id") Long id);

}
