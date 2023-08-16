package com.likelion.loco.service;

import com.likelion.loco.dto.ReviewReq;
import com.likelion.loco.dto.ReviewRes;
import com.likelion.loco.entities.Review;
import com.likelion.loco.entities.Store;
import com.likelion.loco.entities.User;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.repository.ReviewRepository;
import com.likelion.loco.repository.StoreRepository;
import com.likelion.loco.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public BaseResponseStatus updateReview(Long reviewIdx, ReviewReq.reviewUpdateReq reviewUpdateReq){
        try {
            Review review = reviewRepository.findById(reviewIdx).get();
            review.setReviewContent(reviewUpdateReq.getReviewContent());
            review.setReviewStar(reviewUpdateReq.getReviewStar());
            reviewRepository.save(review);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ReviewRes.ReviewListRes getAllReviewByStoreIdx(Long storeIdx){
        try{
            List<Review> review = reviewRepository.findReviewsByStore(storeRepository.findById(storeIdx).get());
            return new ReviewRes.ReviewListRes(review.get(0).getStore(),review.get(0).getUser(),review);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<ReviewRes.ReviewListRes> getAllMyReview(Long userIdx) {
        try {
            List<Review> reviewList = reviewRepository.findReviewsByUser(userRepository.findByUserIdx(userIdx).get());
            Map<Long, List<Review>> reviewMapByStoreIdx = new HashMap<>();

            // 리뷰를 storeIdx 별로 맵에 그룹화
            for (Review review : reviewList) {
                Long storeIdx = review.getStore().getStoreIdx();
                reviewMapByStoreIdx.computeIfAbsent(storeIdx, k -> new ArrayList<>()).add(review);
            }

            List<ReviewRes.ReviewListRes> reviewListRes = new ArrayList<>();

            // 리뷰 맵을 기반으로 ReviewListRes 객체 생성
            for (Map.Entry<Long, List<Review>> entry : reviewMapByStoreIdx.entrySet()) {
                Long storeIdx = entry.getKey();
                Store store = storeRepository.findById(storeIdx).orElse(null);
                User user = userRepository.findByUserIdx(userIdx).orElse(null);

                if (store != null && user != null) {
                    ReviewRes.ReviewListRes storeReviewList = new ReviewRes.ReviewListRes(
                            store,
                            user,
                            entry.getValue()
                    );
                    reviewListRes.add(storeReviewList);
                }
            }

            return reviewListRes;
        } catch (Exception e) {
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
