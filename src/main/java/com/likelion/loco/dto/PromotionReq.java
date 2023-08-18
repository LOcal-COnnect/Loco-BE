package com.likelion.loco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PromotionReq {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PromotionCreateReq{
        private Long sellerIdx;
        private String promotionTitle;
        private String promotionContent;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PromotionUpdateReq {
        private String promotionTitle;
        private String promotionContent;
    }


}
