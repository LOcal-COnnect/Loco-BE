package com.likelion.loco.dto;

import com.likelion.loco.entities.Comment;
import com.likelion.loco.entities.Review;
import com.likelion.loco.entities.Store;
import com.likelion.loco.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ReviewRes {

    @Getter
    @Setter
    public static class ReviewListRes {
        private Long storeIdx;
        private String storeName;
        private String reviewer;
        private Review review;



        // 생성자, getter, setter 등 필요한 메서드 추가

        // 예시로 생성자 작성
        public ReviewListRes(Store store, User user, Review review) {
            this.storeIdx = store.getStoreIdx();
            this.storeName = store.getStoreName();
            this.reviewer = user.getUserName();
            this.review = review;

        }
    }

}
