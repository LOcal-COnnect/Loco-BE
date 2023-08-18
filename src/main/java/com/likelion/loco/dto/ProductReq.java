package com.likelion.loco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ProductReq {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class ProductCreateReq {
        private String productName;
        private Integer productPrice;
    }
}
