package com.likelion.loco.service;

import com.likelion.loco.dto.ReviewReq;
import com.likelion.loco.dto.ReviewRes;
import com.likelion.loco.entities.Review;
import com.likelion.loco.entities.Store;
import com.likelion.loco.entities.User;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.jwt.TokenProvider;
import com.likelion.loco.repository.ReviewRepository;
import com.likelion.loco.repository.StoreRepository;
import com.likelion.loco.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final TokenProvider tokenProvider;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, StoreRepository storeRepository, TokenProvider tokenProvider) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.tokenProvider = tokenProvider;
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

    public BaseResponseStatus updateReview(String token,Long reviewIdx, ReviewReq.reviewUpdateReq reviewUpdateReq){
        try {
            Review review = reviewRepository.findById(reviewIdx).get();
            System.out.println("reviewing user Id : "+tokenProvider.extractUserIdFromBearerToken(token)+" "+review.getUser().getUserId());

            if (review.getUser().getUserId().equals(tokenProvider.extractUserIdFromBearerToken(token))){ //본인인증 로직 혹시 몰라서 넣어둠
                review.setReviewContent(reviewUpdateReq.getReviewContent());
                review.setReviewStar(reviewUpdateReq.getReviewStar());
                reviewRepository.save(review);
                return BaseResponseStatus.SUCCESS;
            }
            else {
                return BaseResponseStatus.UPDATE_AUTHORIZED_ERROR;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ReviewRes.ReviewListRes getAllReviewByStoreIdx(Long storeIdx) {
        try {
            Optional<Store> storeOptional = storeRepository.findById(storeIdx);
            if (storeOptional.isPresent()) {
                Store store = storeOptional.get();
                List<Review> reviewList = reviewRepository.findReviewsByStore(store);

                if (!reviewList.isEmpty()) {
                    return new ReviewRes.ReviewListRes(store, reviewList.get(0).getUser(), reviewList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ReviewRes.MyReviewListRes> getAllMyReview(Long userIdx) {
        try {
            List<Review> reviewList = reviewRepository.findReviewsByUser(userRepository.findByUserIdx(userIdx).get());
            Map<Long, List<Review>> reviewMapByStoreIdx = new HashMap<>();

            // 리뷰를 storeIdx 별로 맵에 그룹화
            for (Review review : reviewList) {
                Long storeIdx = review.getStore().getStoreIdx();
                reviewMapByStoreIdx.computeIfAbsent(storeIdx, k -> new ArrayList<>()).add(review);
            }

            List<ReviewRes.MyReviewListRes> reviewListRes = new ArrayList<>();

            // 리뷰 맵을 기반으로 ReviewListRes 객체 생성
            for (Map.Entry<Long, List<Review>> entry : reviewMapByStoreIdx.entrySet()) {
                Long storeIdx = entry.getKey();
                Store store = storeRepository.findById(storeIdx).orElse(null);
                User user = userRepository.findByUserIdx(userIdx).orElse(null);

                if (store != null && user != null) {
                    ReviewRes.MyReviewListRes storeReviewList = new ReviewRes.MyReviewListRes(
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
