package com.likelion.loco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class StoreReq {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class StoreCreateReq{
        private Long sellerIdx;
        private String storeName;
        private String storeLocation;
        private String storePhone;
        private String storeTel;
        private String storeDesc;
        private String category;
        private String businessNumber;
        private List<ProductReq.ProductCreateReq> productList;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class StoreUpdateReq {
        private String storeName;
        private String storeLocation;
        private String storePhone;
        private String storeTel;
        private String storeDesc;
        private String category;
        private List<ProductReq.ProductCreateReq> productList;
    }

}
