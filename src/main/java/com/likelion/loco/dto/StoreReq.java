package com.likelion.loco.dto;

import com.likelion.loco.entities.Category;
import com.likelion.loco.entities.Seller;
import com.likelion.loco.entities.Store;
import com.likelion.loco.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class StoreReq {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class StoreCreateReq{
        private String storeName;
        private String storePhone;
        private String storeTel;
        private String storeDesc;
        private String storeAddress;
        private String storeDetailAddress;
        private String businessNumber;

        public Store toEntity(Seller seller, Category category){
            return Store.builder()
                    .storeName(this.storeName)
                    .storePhone(this.storePhone)
                    .storeTel(this.storeTel)
                    .storeDesc(this.storeDesc)
                    .storeAddress(this.storeAddress)
                    .storeDetailAddress(this.storeDetailAddress)
                    .seller(seller)
                    .businessNumber(this.businessNumber)
                    .build();
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class StorePromotionCreateReq{
        private String storeName;
    }
}
