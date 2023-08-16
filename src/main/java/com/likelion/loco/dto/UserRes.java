package com.likelion.loco.dto;

import com.likelion.loco.entities.Seller;
import com.likelion.loco.entities.User;
import com.likelion.loco.global.enums.RoleType;
import lombok.*;

public class UserRes {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserGetRes{
        private User user;
    }
    @Getter
    @Setter
    public static class LoginRes{
        private User user;
        private String jwtToken;
        private String status;
        private Seller seller;
        private RoleType roleType;

        public LoginRes(String jwtToken, User user1) {
            this.jwtToken = jwtToken;
            this.user = user1;
            this.roleType = user1.getRoleType();
        }
        public LoginRes(String jwtToken, Seller seller) {
            this.jwtToken = jwtToken;
            this.seller = seller;
            this.roleType = seller.getRoleType();
        }
        public LoginRes(String status){
            this.status = status;
        }
    }
    @Getter
    @Setter
    public static class UpdateRes{
        private User user;
        private String jwtToken;
        private String status;
        private Seller seller;
        private RoleType roleType;

        public UpdateRes(String jwtToken, User user1) {
            this.jwtToken = jwtToken;
            this.user = user1;
            this.roleType = user1.getRoleType();
        }
        public UpdateRes(String jwtToken, Seller seller) {
            this.jwtToken = jwtToken;
            this.seller = seller;
            this.roleType = seller.getRoleType();
        }
        public UpdateRes(String status){
            this.status = status;
        }
    }

}