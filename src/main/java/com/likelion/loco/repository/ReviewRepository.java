package com.likelion.loco.repository;

import com.likelion.loco.entities.Review;
import com.likelion.loco.entities.Store;
import com.likelion.loco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
//    @Query(value = "SELECT avg(review.reviewStar) from Review review JOIN review.store s where s.storeIdx = :id")
//    Float findAvgReview(@Param("id") Long id);

//    @Query(value = "SELECT count(review) from Review review JOIN review.store s where s.storeIdx = :id")
//    Integer countById(@Param("id") Long id);

    List<Review> findReviewsByStore(Store store);
    List<Review> findReviewsByUser(User user);
    @Query(value = "SELECT avg(review.reviewStar) from Review review join review.store s where s.storeIdx = :id")
    Float avgReview(@Param("id") Long id);

}
