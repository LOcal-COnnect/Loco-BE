package com.likelion.loco.service;

import com.likelion.loco.dto.GoodRes;
import com.likelion.loco.entities.Good;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.repository.GoodRepository;
import com.likelion.loco.repository.PromotionRepository;
import com.likelion.loco.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoodService {
    private final GoodRepository goodRepository;
    private final UserRepository userRepository;
    private final PromotionRepository promotionRepository;

    public GoodService(GoodRepository goodRepository, UserRepository userRepository, PromotionRepository promotionRepository) {
        this.goodRepository = goodRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
    }

    public BaseResponseStatus createGood(Long userIdx, Long promotionIdx){
        try{
            goodRepository.save(Good.builder()
                    .user(userRepository.findByUserIdx(userIdx).get())
                    .promotion(promotionRepository.findPromotionByPromotionIdx(promotionIdx).get())
                    .build()
            );
            return BaseResponseStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Integer getCountGood(Long promotionIdx){
        try{
            Integer count = goodRepository.countByPromotion(promotionRepository.findPromotionByPromotionIdx(promotionIdx).get());

            return count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Boolean goodIsChecked(Long userIdx,Long promotionIdx){
        try{
            Optional<Good> good = goodRepository.findGoodByUserAndPromotion(userRepository.findByUserIdx(userIdx).get(),promotionRepository.findPromotionByPromotionIdx(promotionIdx).get());
            if(good.isPresent()){
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public void deleteGood(Long userIdx, Long promotionIdx){
        try{
            goodRepository.deleteAllByUserAndPromotion(userRepository.findByUserIdx(userIdx).get(),promotionRepository.findPromotionByPromotionIdx(promotionIdx).get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
