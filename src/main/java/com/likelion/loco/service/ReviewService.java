package com.likelion.loco.service;

import com.likelion.loco.dto.ReviewReq;
import com.likelion.loco.dto.ReviewRes;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.repository.ReviewRepository;
import com.likelion.loco.repository.StoreRepository;
import com.likelion.loco.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, StoreRepository storeRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }

    public BaseResponseStatus createReview(Long userIdx, Long storeIdx, ReviewReq.reviewCreateReq reviewCreateReq){
        try{
            reviewRepository.save(reviewCreateReq.toEntity(userRepository.findByUserIdx(userIdx).get(),storeRepository.findById(storeIdx).get()));
            return BaseResponseStatus.SUCCESS;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ReviewRes.ReviewListRes getAllReviewByStoreIdx(Long storeIdx){
        try{
            System.out.println(reviewRepository.findReviewsByStore(storeRepository.findById(storeIdx).get()));
            return new ReviewRes.ReviewListRes(reviewRepository.findReviewsByStore(storeRepository.findById(storeIdx).get()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ReviewRes.ReviewListRes getAllMyReview (Long userIdx){
        try{
            System.out.println(reviewRepository.findReviewsByUser(userRepository.findByUserIdx(userIdx).get()));
            return new ReviewRes.ReviewListRes(reviewRepository.findReviewsByUser(userRepository.findByUserIdx(userIdx).get()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void deleteMyReview(Long reviewIdx){
        try{
            reviewRepository.deleteById(reviewIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
