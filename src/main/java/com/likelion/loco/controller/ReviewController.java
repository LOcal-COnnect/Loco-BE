package com.likelion.loco.controller;

import com.likelion.loco.dto.ReviewReq;
import com.likelion.loco.dto.ReviewRes;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.service.ReviewService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{storeidx}/users/{useridx}")
    public BaseResponseStatus reviewCreate(@PathVariable("storeidx") Long storeIdx, @PathVariable("useridx") Long userIdx, @RequestBody ReviewReq.reviewCreateReq reviewCreateReq){
        try{
            return reviewService.createReview(userIdx,storeIdx,reviewCreateReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/stores/{storeidx}")
    public ReviewRes.ReviewListRes getAllReviewsByStoreIdx(@PathVariable("storeidx") Long storeIdx){
        try{
            return reviewService.getAllReviewByStoreIdx(storeIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/users/{useridx}")
    public ReviewRes.ReviewListRes getAllMyReviews(@PathVariable("useridx") Long userIdx){
        try{
            return reviewService.getAllMyReview(userIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @DeleteMapping("/{reviewidx}")
    public ResponseEntity<HttpStatus> deleteMyReview(@PathVariable("reviewidx") Long reviewIdx){
        try{
            reviewService.deleteMyReview(reviewIdx);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
