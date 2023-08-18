package com.likelion.loco.service;


import com.likelion.loco.entities.User;
import com.likelion.loco.global.BaseException;
import com.likelion.loco.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.likelion.loco.global.BaseResponseStatus.POST_USERS_NOT_FOUND_EMAIL;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //임시비밀번호 발급 및 재설정
    @Transactional
    public boolean updatePassword(Long userIdx, String newPassword) throws BaseException {
        Optional<User> user = userRepository.findById(userIdx);
        user.ifPresent(u -> {
            u.setUserPassword(newPassword);
            userRepository.saveAndFlush(u);
        });
        return true;
    }

    public boolean emailValidation(String email) throws BaseException {
        Optional<User> optionalUser = userRepository.findByUserEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getUserEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


    public Long findUserByEmail(String email) throws BaseException{
        Optional<User> optionalUser = userRepository.findByUserEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getUserIdx();
        } else {
            throw new BaseException(POST_USERS_NOT_FOUND_EMAIL);
        }
    }


}
