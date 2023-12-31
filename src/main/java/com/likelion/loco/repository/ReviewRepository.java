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

    List<Review> findReviewsByStore(Store store);
    List<Review> findReviewsByUser(User user);

    @Query(value = "SELECT avg(review.reviewStar) from Review review join review.store s where s.storeIdx = :id")
    Float avgReview(@Param("id") Long id);

}
