package com.likelion.loco.dto;

import com.likelion.loco.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReviewReq {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class reviewCreateReq {
        private String reviewContent;
        private Integer reviewStar;

        public Review toEntity(User user1, Store store){
            return Review.builder()
                    .reviewContent(this.reviewContent)
                    .reviewStar(this.reviewStar)
                    .user(user1)
                    .store(store)
                    .build();
        }
    }
}
