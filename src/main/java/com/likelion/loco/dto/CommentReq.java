package com.likelion.loco.dto;

import com.likelion.loco.entities.Comment;
import com.likelion.loco.entities.Promotion;
import com.likelion.loco.entities.Seller;
import com.likelion.loco.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CommentReq {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class commentCreateReq {
        private String commentContent;
        public Comment toEntity(User user1, Promotion promotion){
            return Comment.builder()
                    .commentContent(this.commentContent)
                    .user(user1)
                    .promotion(promotion)
                    .build();
        }
        public Comment toEntity(Seller seller, Promotion promotion){
            return Comment.builder()
                    .commentContent(this.commentContent)
                    .seller(seller)
                    .promotion(promotion)
                    .build();
        }
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class commentUpdateReq {
        private String commentContent;

        }
}

