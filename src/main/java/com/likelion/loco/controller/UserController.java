package com.likelion.loco.controller;

import com.likelion.loco.dto.SellerReq;
import com.likelion.loco.dto.SellerRes;
import com.likelion.loco.dto.UserReq;
import com.likelion.loco.dto.UserRes;
import com.likelion.loco.global.BaseEntity;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.jwt.TokenProvider;
import com.likelion.loco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    public UserController(UserService userService, TokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public UserRes.LoginRes userLogin(@RequestBody UserReq.LoginReq userLoginReq){
        try{;
            return userService.Login(userLoginReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    @PostMapping("/join")
    public BaseResponseStatus userRegister(@RequestBody UserReq.UserCreateReq userCreateReq){
        try{
            return userService.userRegister(userCreateReq);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    @PostMapping("/sellers/join")
    public BaseResponseStatus sellerRegister(@RequestBody SellerReq.SellerCreateReq sellerCreateReq){
        try{
            return userService.sellerRegister(sellerCreateReq);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }




    @GetMapping("{useridx}")
    public UserRes.UserGetRes getUserInfo(@PathVariable("useridx") Long userIdx){
        try{
            return userService.getUserInfo(userIdx);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    @GetMapping("/sellers/{sellerIdx}")
    public SellerRes.GetSellerInfo getSellerInfo(@PathVariable("sellerIdx") Long sellerIdx){
        try{
            return userService.getSellerInfo(sellerIdx);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    @PatchMapping("{useridx}")
    public UserRes.UpdateRes updateUserInfo(@PathVariable("useridx") Long userIdx, @RequestBody UserReq.UserUpdateReq userUpdateReq){
        try{
            return userService.userUpdate(userIdx, userUpdateReq);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    @PatchMapping("/sellers/{sellerIdx}")
    public UserRes.UpdateRes updateSellerInfo(@PathVariable("sellerIdx") Long sellerIdx, @RequestBody SellerReq.SellerUpdateReq sellerUpdateReq){
        try{
            return userService.sellerUpdate(sellerIdx, sellerUpdateReq);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    @DeleteMapping("/{userIdx}")
    public BaseResponseStatus deleteUser(@PathVariable("userIdx") Long userIdx){
        try {
            userService.userDelete(userIdx);
            return BaseResponseStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @DeleteMapping("/sellers/{sellerIdx}")
    public BaseResponseStatus deleteSeller(@PathVariable("sellerIdx") Long sellerIdx){
        try {
            userService.sellerDelete(sellerIdx);
            return BaseResponseStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        // JWT 토큰 무효화 처리
        String token = tokenProvider.extractTokenFromRequest(request);
        if (token != null) {
            tokenProvider.invalidateToken(token);
        }
    }

}
