package com.likelion.loco.controller;

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

    private final EmailService emailService;

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
