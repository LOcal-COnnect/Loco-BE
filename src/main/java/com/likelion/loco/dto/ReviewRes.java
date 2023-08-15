package com.likelion.loco.dto;

import com.likelion.loco.entities.Comment;
import com.likelion.loco.entities.Review;
import com.likelion.loco.entities.Store;
import com.likelion.loco.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class ReviewRes {
    @Getter
    @Setter
    public static class ReviewListRes{
        private Long storeIdx;
        private String storeName;
        private String reviewer;
        private List<Review> reviewList;
        public ReviewListRes(Store store, User user, List<Review> review) {
            this.storeIdx = store.getStoreIdx();
            this.storeName = store.getStoreName();
            this.reviewer = user.getUserName();
            this.reviewList = review;
        }

    }

}
