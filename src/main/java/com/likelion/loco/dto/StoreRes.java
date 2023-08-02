package com.likelion.loco.dto;

import com.likelion.loco.entities.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class StoreRes {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class StoreCreateRes{
        private Store store;
    }
}
