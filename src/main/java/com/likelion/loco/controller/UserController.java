package com.likelion.loco.controller;

import com.likelion.loco.dto.SellerReq;
import com.likelion.loco.dto.SellerRes;
import com.likelion.loco.dto.UserReq;
import com.likelion.loco.dto.UserRes;
import com.likelion.loco.entities.User;
import com.likelion.loco.global.BaseEntity;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.jwt.TokenProvider;
import com.likelion.loco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

import com.likelion.loco.global.BaseException;
import com.likelion.loco.global.BaseResponse;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.service.EmailService;
import com.likelion.loco.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final EmailService emailService;


    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody UserReq.LoginReq userLoginReq){
        try{;
            UserRes.LoginRes loginRes = userService.Login(userLoginReq);
            if (loginRes.getSuccess()){ //성공이라면
                System.out.println("true");
                return new ResponseEntity<>(loginRes,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(loginRes, HttpStatus.valueOf(400));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    @PostMapping("/join")
    public ResponseEntity userRegister(@RequestBody UserReq.UserCreateReq userCreateReq){
        try{
            BaseResponseStatus baseResponseStatus = userService.userRegister(userCreateReq);
            if(baseResponseStatus.isSuccess()){
                return new ResponseEntity<>(baseResponseStatus.getMessage(),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(baseResponseStatus.getMessage(),HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping("/sellers/join")
    public ResponseEntity sellerRegister(@RequestBody SellerReq.SellerCreateReq sellerCreateReq){
        try{
            BaseResponseStatus baseResponseStatus = userService.sellerRegister(sellerCreateReq);
            if(baseResponseStatus.isSuccess()){
                return new ResponseEntity<>(baseResponseStatus.getMessage(),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(baseResponseStatus.getMessage(), HttpStatus.valueOf(400));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }


    @GetMapping("{useridx}")
    public ResponseEntity getUserInfo(@PathVariable("useridx") Long userIdx){
        try{
            UserRes.UserGetRes userGetRes = userService.getUserInfo(userIdx);
            if(userGetRes !=null){
                return new ResponseEntity<>(userGetRes,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/sellers/{sellerIdx}")
    public ResponseEntity getSellerInfo(@PathVariable("sellerIdx") Long sellerIdx){
        try{
            SellerRes.GetSellerInfo getSellerInfo = userService.getSellerInfo(sellerIdx);
            if(getSellerInfo != null){
                return new ResponseEntity<>(getSellerInfo,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("{useridx}")
    public ResponseEntity updateUserInfo(@PathVariable("useridx") Long userIdx, @RequestBody UserReq.UserUpdateReq userUpdateReq){
        try{
            UserRes.UpdateRes updateRes = userService.userUpdate(userIdx, userUpdateReq);
            if(updateRes !=null){
                return new ResponseEntity<>(updateRes,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("/sellers/{sellerIdx}")
    public ResponseEntity updateSellerInfo(@PathVariable("sellerIdx") Long sellerIdx, @RequestBody SellerReq.SellerUpdateReq sellerUpdateReq){
        try{
            UserRes.UpdateRes updateRes = userService.sellerUpdate(sellerIdx,sellerUpdateReq);
            if(updateRes !=null){
                return new ResponseEntity<>(updateRes,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{userIdx}")
    public ResponseEntity<BaseResponseStatus> deleteUser(@PathVariable("userIdx") Long userIdx) {
        try {
            userService.userDelete(userIdx);
            return new ResponseEntity<>(BaseResponseStatus.SUCCESS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/sellers/{sellerIdx}")
    public ResponseEntity<BaseResponseStatus> deleteSeller(@PathVariable("sellerIdx") Long sellerIdx) {
        try {
            userService.sellerDelete(sellerIdx);
            return new ResponseEntity<>(BaseResponseStatus.SUCCESS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // JWT 토큰 무효화 처리
            String token = tokenProvider.extractTokenFromRequest(request);
            if (token != null) {
                tokenProvider.invalidateToken(token);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //이메일 인증
    @PostMapping("/password")
    public BaseResponse<String> emailConfirm(@RequestParam String email) throws Exception {
        if(userService.emailValidation(email)) {
            try {
                String ePw = emailService.sendSimpleMessage(email);
//                이메일 임시비밀번호 발급 및 저장 코드
                userService.updatePassword(userService.findUserByEmail(email), ePw);
                return new BaseResponse<>(ePw);
            } catch (BaseException exception) {
                return new BaseResponse<>(exception.getStatus());
            }
        } else return new BaseResponse<>(BaseResponseStatus.POST_USERS_INVALID_EMAIL);
    }
}
