package com.likelion.loco.dto;

import com.likelion.loco.entities.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class GoodRes {
    @NoArgsConstructor
    @Getter
    @Setter
    public static class GoodGetRes{
        private Integer goodCount;
        private Boolean isCheck;

//        public GoodGetRes toChange(Integer count, Boolean isCheck){
//            this.goodCount = count;
//            this.isCheck = isCheck;
//            return new GoodGetRes(count)
//        }
    }
}
