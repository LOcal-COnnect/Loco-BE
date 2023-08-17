package com.likelion.loco.service;

import com.likelion.loco.dto.SellerReq;
import com.likelion.loco.dto.SellerRes;
import com.likelion.loco.dto.UserReq;
import com.likelion.loco.dto.UserRes;
import com.likelion.loco.entities.Seller;
import com.likelion.loco.entities.Store;
import com.likelion.loco.entities.User;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.global.enums.RoleType;
import com.likelion.loco.jwt.TokenProvider;
import com.likelion.loco.repository.SellerRepository;
import com.likelion.loco.repository.StoreRepository;
import com.likelion.loco.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final TokenProvider tokenProvider;
    private final StoreRepository storeRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private boolean doesUserExist(String userId) {return userRepository.existsByUserId(userId);}
    private boolean doesSellerExist(String sellerId){return sellerRepository.existsBySellerId(sellerId);}
    private boolean isDuplicateId(String userId, Long userIdx) {
        return userRepository.existsByUserIdAndUserIdxNot(userId, userIdx) ||
                sellerRepository.existsBySellerIdAndSellerIdxNot(userId, userIdx);
    }
    public UserService(UserRepository userRepository, SellerRepository sellerRepository, TokenProvider tokenProvider, StoreRepository storeRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.tokenProvider = tokenProvider;
        this.storeRepository = storeRepository;
    }

    public BaseResponseStatus userRegister(UserReq.UserCreateReq userCreateReq){
        try{
            if(!doesUserExist(userCreateReq.getUserId()) && !doesSellerExist(userCreateReq.getUserId())){ //Buyer와 Seller에 이미 있는 유저가 아니라면
                userCreateReq.setUserPassword(bCryptPasswordEncoder.encode(userCreateReq.getUserPassword()));
                userRepository.save(userCreateReq.toEntity(RoleType.BUYER));
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
    public BaseResponseStatus sellerRegister(SellerReq.SellerCreateReq sellerCreateReq){
        try{
            if(!doesUserExist(sellerCreateReq.getSellerId()) && !doesSellerExist(sellerCreateReq.getSellerId())){ //Buyer와 Seller에 이미 있는 유저가 아니라면
                sellerCreateReq.setSellerPassword(bCryptPasswordEncoder.encode(sellerCreateReq.getSellerPassword()));
                sellerRepository.save(sellerCreateReq.toEntity(RoleType.SELLER));

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

    public UserRes.LoginRes Login(UserReq.LoginReq userLoginReq){
        try{
            Optional<User> user = userRepository.findByUserId(userLoginReq.getUserId()); //암호화 해서 둘이 같은지 확인
            Optional<Seller> seller = sellerRepository.findBySellerId(userLoginReq.getUserId());
            if (user.isPresent()){ //유저가 존재한다면.
                User user1 = user.get();
                if(bCryptPasswordEncoder.matches(userLoginReq.getUserPassword(), user1.getUserPassword())){
                    String jwtToken = tokenProvider.create(user1.getUserId(), RoleType.BUYER);
                    String tokenDecode = tokenProvider.validateAndGetUserId(jwtToken);
                    System.out.println(tokenDecode);
                    System.out.println(tokenDecode.getClass());
                    return new UserRes.LoginRes(jwtToken,user1);
                }
                else { //비밀번호가 틀렸을시;
                    return new UserRes.LoginRes("FAILED_TO_LOGIN");
                }
            }
            else if (seller.isPresent()){ //만약 seller가 아니라 user라면
                Seller seller1 = seller.get();
                if(bCryptPasswordEncoder.matches(userLoginReq.getUserPassword(), seller1.getSellerPassword())) {
                    String jwtToken = tokenProvider.create(seller1.getSellerId(), RoleType.SELLER);
                    String tokenDecode = tokenProvider.validateAndGetUserId(jwtToken);
                    System.out.println(tokenDecode);
                    System.out.println(tokenDecode.getClass());
                    return new UserRes.LoginRes(jwtToken,seller1);
                }
            }
            else { //없는 유저일 시
                return new UserRes.LoginRes("FAILED_TO_LOGIN");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public UserRes.UpdateRes userUpdate(Long userIdx, UserReq.UserUpdateReq userUpdateReq) {
        try{
            Optional<User> user = userRepository.findByUserIdx(userIdx);
                if (user.isPresent()) {
                    User user1 = user.get();
                    boolean isDuplicate = isDuplicateId(userUpdateReq.getUserId(),userIdx);
                    if(!isDuplicate) {
                        user1.setUserName(userUpdateReq.getUserName());
                        user1.setUserId(userUpdateReq.getUserId());
                        user1.setUserPassword(bCryptPasswordEncoder.encode(userUpdateReq.getUserPassword()));
                        user1.setUserEmail(userUpdateReq.getUserEmail());
                        user1.setUserAddress(userUpdateReq.getUserAddress());
                        user1.setUserDetailAddress(userUpdateReq.getUserDetailAddress());
                        user1.setUserPhone(userUpdateReq.getUserPhone());


                        userRepository.save(user1);

                        return new UserRes.UpdateRes(tokenProvider.create(userUpdateReq.getUserId(), user1.getRoleType()),user1);
                    }
                    else{
                        return new UserRes.UpdateRes(BaseResponseStatus.USERS_DUPLICATED.getMessage());
                    }
                }
                else{
                    //return new UserRes.UpdateRes("FAILED_TO_UPDATE");
                    return new UserRes.UpdateRes(BaseResponseStatus.USERS_EMPTY_USER_ID.getMessage());
                }
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    public UserRes.UpdateRes sellerUpdate(Long sellerIdx, SellerReq.SellerUpdateReq sellerUpdateReq) {
        try {
            Optional<Seller> seller = sellerRepository.findById(sellerIdx);
            if (seller.isPresent()) {
                Seller user1 = seller.get();

                // Check for duplicate sellerId
                boolean isDuplicate = isDuplicateId(sellerUpdateReq.getSellerId(),sellerIdx);
                if (!isDuplicate) {
                    user1.setSellerName(sellerUpdateReq.getSellerName());
                    user1.setSellerId(sellerUpdateReq.getSellerId());
                    user1.setSellerPassword(bCryptPasswordEncoder.encode(sellerUpdateReq.getSellerPassword()));
                    user1.setSellerEmail(sellerUpdateReq.getSellerEmail());
                    user1.setSellerPhone(sellerUpdateReq.getSellerPhone());

                    sellerRepository.save(user1);

                    return new UserRes.UpdateRes(tokenProvider.create(sellerUpdateReq.getSellerId(), user1.getRoleType()),user1);

                } else {
                    return new UserRes.UpdateRes(BaseResponseStatus.USERS_DUPLICATED.getMessage());
                }
            }
            else{
                return new UserRes.UpdateRes(BaseResponseStatus.USERS_EMPTY_USER_ID.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public SellerRes.GetSellerInfo getSellerInfo(Long sellerIdx){ //마이페이지 때 쓸 예정
        return new SellerRes.GetSellerInfo(sellerRepository.findById(sellerIdx).get());
    }
    public UserRes.UserGetRes getUserInfo(Long userIdx){ //마이페이지 때 쓸 예정
        return new UserRes.UserGetRes(userRepository.findById(userIdx).get());
    }

    public void userDelete(Long userIdx){ //회원탈퇴
        try {
            userRepository.deleteById(userIdx);
        }catch (Exception e) {e.printStackTrace();}
    }
    public void sellerDelete(Long sellerIdx){ //회원탈퇴
        try {
            sellerRepository.deleteById(sellerIdx);
        }catch (Exception e) {e.printStackTrace();}
    }
}
