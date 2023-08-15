package com.likelion.loco.service;

import com.likelion.loco.dto.CommentReq;
import com.likelion.loco.dto.CommentRes;
import com.likelion.loco.entities.Comment;
import com.likelion.loco.entities.Promotion;
import com.likelion.loco.entities.Seller;
import com.likelion.loco.entities.User;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.jwt.TokenProvider;
import com.likelion.loco.repository.CommentRepository;
import com.likelion.loco.repository.PromotionRepository;
import com.likelion.loco.repository.SellerRepository;
import com.likelion.loco.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CommentService {

    private final CommentRepository commentRepository;
    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final PromotionRepository promotionRepository;
    private final TokenProvider tokenProvider;


    public CommentService(CommentRepository commentRepository, SellerRepository sellerRepository, UserRepository userRepository, PromotionRepository promotionRepository, TokenProvider tokenProvider) {
        this.commentRepository = commentRepository;
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
        this.tokenProvider = tokenProvider;
    }

    public BaseResponseStatus createComment(String userRole,Long userIdx, Long promotionIdx, CommentReq.commentCreateReq commentCreateReq) {
        try {
            //String userRole = tokenProvider.extractRoleFromBearerToken();
            if ("BUYER".equals(userRole)) {
                User user = userRepository.findByUserIdx(userIdx).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
                Promotion promotion = promotionRepository.findPromotionByPromotionIdx(promotionIdx).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
                commentRepository.save(commentCreateReq.toEntity(user, promotion));
            } else if ("SELLER".equals(userRole)) {
                Seller seller = sellerRepository.findById(userIdx).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
                Promotion promotion = promotionRepository.findPromotionByPromotionIdx(promotionIdx).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
                commentRepository.save(commentCreateReq.toEntity(seller, promotion));
            } else {
                throw new IllegalArgumentException("Invalid user role");
            }

            return BaseResponseStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            // 에러 핸들링 로직 추가
        }
        return null; // 에러 핸들링 로직 추가
    }

    public BaseResponseStatus updateComment(Long commentIdx, CommentReq.commentUpdateReq commentUpdateReq){
        try{
            Optional<Comment> comment = commentRepository.findByCommentIdx(commentIdx);
            if(comment.isPresent()){
                Comment comment1 =  comment.get();
                comment1.setCommentContent(commentUpdateReq.getCommentContent());
                commentRepository.save(comment1);
            }else{
                return BaseResponseStatus.MODIFY_FAIL_COMMENT;
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public CommentRes.CommentListRes getCommentByPromotionIdx(Long promotionIdx){
        return new CommentRes.CommentListRes(commentRepository.findCommentsByPromotion(promotionRepository.findById(promotionIdx).get()));
    }
    public void deleteByCommentIdx(Long commentIdx){
        commentRepository.deleteById(commentIdx);
    }

}
