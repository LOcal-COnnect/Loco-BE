package com.likelion.loco.dto;

import com.likelion.loco.entities.Comment;
import com.likelion.loco.entities.Promotion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class PromotionRes {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PromotionCreateRes{
        private Promotion promotion;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PromotionDetailRes{
        private String promotionTitle;
        private String promotionContent;
        private Integer viewCount;
        private Integer goods;
        private String storeName;
        private List<Comment> comments;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PromotionListRes{
        private Long promotionIdx;
        private LocalDateTime createdAt;
        private Integer viewCount;
        private Integer goods;
        private String promotionTitle;
        private String promotionContent;
        private String storeName;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PromotionMyListRes{
        private Long promotionIdx;
        private LocalDateTime createdAt;
        private String promotionTitle;
        private String promotionContent;
    }


}
