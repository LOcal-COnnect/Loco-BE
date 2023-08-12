package com.likelion.loco.service;

import com.likelion.loco.dto.UserReq;
import com.likelion.loco.dto.UserRes;
import com.likelion.loco.entities.User;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.repository.UserRepository;
import com.likelion.loco.util.ExceptionUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private boolean doesUserExist(String userId) {
        return userRepository.findByUserId(userId).isPresent();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BaseResponseStatus userRegister(UserReq.UserCreateReq userCreateReq){
        try{
            if(!doesUserExist(userCreateReq.getUserId())){ //이미 있는 유저가 아니라면
                userCreateReq.setUserPassword(bCryptPasswordEncoder.encode(userCreateReq.getUserPassword()));
                userRepository.save(userCreateReq.toEntity());
                return BaseResponseStatus.SUCCESS;
            }
            else{
                return BaseResponseStatus.USERS_ALREADY_EXIST;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public BaseResponseStatus userLogin(UserReq.UserLoginReq userLoginReq){
        try{
            Optional<User> user = userRepository.findByUserId(userLoginReq.getUserId()); //암호화 해서 둘이 같은지 확인
            if (user.isPresent()){ //유저가 존재한다면.
                User user1 = user.get();
                if(bCryptPasswordEncoder.matches(userLoginReq.getUserPassword(), user1.getUserPassword())){
                    return BaseResponseStatus.SUCCESS;
                }
                else { //비밀번호가 틀렸을시;
                    return BaseResponseStatus.FAILED_TO_LOGIN;
                }
            }
            else{ //없는 유저일 시
                return BaseResponseStatus.FAILED_TO_LOGIN;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public BaseResponseStatus userUpdate(Long userIdx, UserReq.UserUpdateReq userUpdateReq) {
        try{
            Optional<User> user = userRepository.findByUserIdx(userIdx);
            System.out.println(user.get().getUserId());
            System.out.println(userUpdateReq.getUserId());
            System.out.println(userUpdateReq.getUserName());
            if (user.isPresent()){
                User user1 = user.get();
                user1.setUserName(userUpdateReq.getUserName());
                user1.setUserId(userUpdateReq.getUserId());
                user1.setUserPassword(bCryptPasswordEncoder.encode(userUpdateReq.getUserPassword()));
                user1.setUserEmail(userUpdateReq.getUserEmail());
                user1.setUserAddress(userUpdateReq.getUserAddress());
                user1.setUserDetailAddress(userUpdateReq.getUserDetailAddress());
                user1.setUserPhone(userUpdateReq.getUserPhone());


                userRepository.save(user1);

                return BaseResponseStatus.SUCCESS;


            }
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    public UserRes.UserGetRes getUserInfo(Long userIdx){ //마이페이지 때 쓸 예정
        return new UserRes.UserGetRes(userRepository.findByUserIdx(userIdx).get());
    }

    public void userDelete(Long userIdx){ //회원탈퇴
        try {
            userRepository.deleteById(userIdx);
        }catch (Exception e) {e.printStackTrace();}
    }
}
