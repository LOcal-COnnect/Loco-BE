package com.likelion.loco.controller;

import com.likelion.loco.dto.UserReq;
import com.likelion.loco.dto.UserRes;
import com.likelion.loco.global.BaseEntity;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public BaseResponseStatus userLogin(@RequestBody UserReq.UserLoginReq userLoginReq){
        try{
            return userService.userLogin(userLoginReq);
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
    public BaseResponseStatus getUserInfo(@PathVariable("useridx") Long userIdx, @RequestBody UserReq.UserUpdateReq userUpdateReq){
        try{
            return userService.userUpdate(userIdx, userUpdateReq);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

}
