package com.likelion.loco.dto;

import com.likelion.loco.entities.User;
import com.likelion.loco.global.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserReq {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserCreateReq {
        private String userId;
        private String userPassword;
        private String userEmail;
        private String userName;
        private String userPhone;
        private String userAddress;
        private String userDetailAddress;


        public User toEntity(RoleType roleType){
            return User.builder()
                    .userId(this.userId)
                    .userName(this.userName)
                    .userPassword(this.userPassword)
                    .userEmail(this.userEmail)
                    .userPhone(this.userPhone)
                    .userAddress(this.userAddress)
                    .userDetailAddress(this.userDetailAddress)
                    .roleType(roleType)
                    .build();
        }

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserUpdateReq {
        private String userId;
        private String userPassword;
        private String userEmail;
        private String userName;
        private String userPhone;
        private String userAddress;
        private String userDetailAddress;


    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class LoginReq {
        private String userId;
        private String userPassword;

    }

}