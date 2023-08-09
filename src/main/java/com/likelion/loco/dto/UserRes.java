package com.likelion.loco.dto;

import com.likelion.loco.entities.User;
import lombok.*;

import javax.persistence.Column;

public class UserRes {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserGetRes{
        private User user;
    }
}