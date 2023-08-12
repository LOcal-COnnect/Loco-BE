package com.likelion.loco.dto;

import com.likelion.loco.entities.Comment;
import com.likelion.loco.entities.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class ReviewRes {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class ReviewListRes{
        private List<Review> reviewList;
    }

}
