package com.likelion.loco.dto;

import com.likelion.loco.entities.Seller;
import com.likelion.loco.global.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SellerReq {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class SellerCreateReq{
        private String sellerId;
        private String sellerName;
        private String sellerPassword;
        private String sellerEmail;
        private String sellerPhone;

        private String storeAddress;
        private String storeDetailAddress;

        private String storeName;
        private String storePhone;
        private String businessNumber;

        public Seller toEntity(RoleType roleType){
            return Seller.builder()
                    .sellerId(this.sellerId)
                    .sellerName(this.sellerName)
                    .sellerPassword(this.sellerPassword)
                    .sellerPhone(this.sellerPhone)
                    .sellerEmail(this.sellerEmail)
                    .roleType(roleType)
                    .build();
        }
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class SellerUpdateReq{
        private String sellerId;
        private String sellerName;
        private String sellerPassword;
        private String sellerEmail;
        private String sellerPhone;
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class SellerLoginReq {
        private String sellerId;
        private String sellerPassword;

    }
}
