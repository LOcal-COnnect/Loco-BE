package com.likelion.loco.dto;

import com.likelion.loco.entities.Seller;
import com.likelion.loco.global.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        private String sellerAddress;
        private String sellerDetailAddress;


        public Seller toEntity(RoleType roleType){
            return Seller.builder()
                    .sellerId(this.sellerId)
                    .sellerName(this.sellerName)
                    .sellerPassword(this.sellerPassword)
                    .sellerPhone(this.sellerPhone)
                    .sellerEmail(this.sellerEmail)
                    .sellerAddress(this.sellerAddress)
                    .sellerDetailAddress(this.sellerDetailAddress)
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
        private String sellerAddress;
        private String sellerDetailAddress;
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
