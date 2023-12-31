package com.likelion.loco.repository;

import com.likelion.loco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByUserIdx(Long userIdx);
    boolean existsByUserId(String userId);
    boolean existsByUserIdAndUserIdxNot(String userId, Long userIdx);
    Optional<User> findByUserEmail(String email);

    
}

