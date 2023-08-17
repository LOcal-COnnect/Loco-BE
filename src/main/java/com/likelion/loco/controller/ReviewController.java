package com.likelion.loco.controller;

import com.likelion.loco.dto.ReviewReq;
import com.likelion.loco.dto.ReviewRes;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.service.ReviewService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{storeidx}/users/{useridx}")
    public ResponseEntity<BaseResponseStatus> reviewCreate(@PathVariable("storeidx") Long storeIdx, @PathVariable("useridx") Long userIdx, @RequestBody ReviewReq.reviewCreateReq reviewCreateReq) {
        try {
            BaseResponseStatus responseStatus = reviewService.createReview(userIdx, storeIdx, reviewCreateReq);
            if (responseStatus.equals(BaseResponseStatus.SUCCESS)) {
                return new ResponseEntity<>(responseStatus, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{reviewIdx}")
    public ResponseEntity<BaseResponseStatus> reviewUpdate(@AuthenticationPrincipal String userId, @PathVariable("reviewIdx") Long reviewIdx, @RequestBody ReviewReq.reviewUpdateReq reviewUpdateReq) {
        try {
            BaseResponseStatus responseStatus = reviewService.updateReview(userId, reviewIdx, reviewUpdateReq);
            if (responseStatus.equals(BaseResponseStatus.SUCCESS)) {
                return new ResponseEntity<>(responseStatus, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/stores/{storeidx}")
    public ResponseEntity<List<ReviewRes.ReviewListRes>> getAllReviewsByStoreIdx(@PathVariable("storeidx") Long storeIdx) {
        try {
            List<ReviewRes.ReviewListRes> reviews = reviewService.getAllReviewByStoreIdx(storeIdx);
            if (reviews != null) {//DB데이터가 존재하는 경우
                return new ResponseEntity<>(reviews, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{useridx}")
    public ResponseEntity<List<ReviewRes.ReviewListRes>> getAllMyReviews(@PathVariable("useridx") Long userIdx) {
        try {
            List<ReviewRes.ReviewListRes> reviews = reviewService.getAllMyReview(userIdx);
            if (reviews != null) {
                return new ResponseEntity<>(reviews, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{reviewidx}")
    public ResponseEntity<HttpStatus> deleteMyReview(@PathVariable("reviewidx") Long reviewIdx) {
        try {
            reviewService.deleteMyReview(reviewIdx);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
