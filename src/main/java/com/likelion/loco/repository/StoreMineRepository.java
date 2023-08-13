package com.likelion.loco.repository;

import com.likelion.loco.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreMineRepository extends JpaRepository<StoreMine,Long> {
    @Query("SELECT COUNT(s) FROM StoreMine s WHERE s.store = :store")
    Integer countByStore(@Param("store") Store store);

    //찜한 여부 체크할때 쓰는 함수
    Optional<StoreMine> findStoreMineByUserAndStore(User user, Store store);

    //내가 찜한 목록 가져올 때 쓰는 확인하는 함수
    List<StoreMine> findStoreMinesByUser(User user);

    void deleteAllByUserAndStore(User user, Store store);

}
