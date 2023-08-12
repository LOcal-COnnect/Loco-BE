package com.likelion.loco.service;

import com.likelion.loco.entities.Like;
import com.likelion.loco.repository.LikeRepository;
import com.likelion.loco.repository.PromotionRepository;
import com.likelion.loco.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PromotionRepository promotionRepository;

    public LikeService(LikeRepository likeRepository, UserRepository userRepository, PromotionRepository promotionRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
    }

    public void createLike(Long userIdx, Long promotionIdx){
        try{
            likeRepository.save(Like.builder()
                    .user(userRepository.findByUserIdx(userIdx).get())
                    .promotion(promotionRepository.findPromotionByPromotionIdx(promotionIdx).get())
                    .build()
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteLike(Long likeIdx){
        try{
            likeRepository.deleteById(likeIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
