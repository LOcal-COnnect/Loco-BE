package com.likelion.loco.dto;

import com.likelion.loco.entities.Review;
import com.likelion.loco.entities.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class StoreRes {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class StoreCreateRes{
        private Store store;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class StoreListRes{
        private Long storeIdx;
        private String storeName;
        private String storeLocation;
    }

    @Getter
    @Setter
    public static class StoreAllGetRes{
        private Store store;
        private List<Review> reviewList;
        private Integer goodCount;
        private Float avgReview;

        public StoreAllGetRes(Store store, Integer goodCount, Float avgReview, List<Review> reviewList1) {
            this.store = store;
            this.goodCount = goodCount;
            this.avgReview = avgReview;
            this.reviewList = reviewList1;
        }
    }





}
