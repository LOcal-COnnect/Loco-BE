package com.likelion.loco.repository;

import com.likelion.loco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //    Optional<User> findByEmail(String email);
    Optional<User> findByUserEmail(String email);




}

