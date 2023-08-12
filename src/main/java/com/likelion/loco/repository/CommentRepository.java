package com.likelion.loco.repository;

import com.likelion.loco.entities.Comment;
import com.likelion.loco.entities.Promotion;
import com.likelion.loco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Comment> findByCommentIdx(Long commentIdx);
    List<Comment> findCommentsByPromotion(Promotion promotion);
}
