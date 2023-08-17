package com.likelion.loco.dto;

import com.likelion.loco.entities.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SellerRes {

    @Getter
    @Setter
    public static class GetSellerInfo{
        private Seller seller;
        public   GetSellerInfo(Seller seller){
            this.seller = seller;
        }

    }

}
