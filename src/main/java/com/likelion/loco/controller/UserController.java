package com.likelion.loco.controller;

import com.likelion.loco.dto.SellerReq;
import com.likelion.loco.dto.UserReq;
import com.likelion.loco.dto.UserRes;
import com.likelion.loco.global.BaseEntity;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.jwt.TokenProvider;
import com.likelion.loco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
