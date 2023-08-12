package com.likelion.loco.service;

import com.likelion.loco.dto.CommentReq;
import com.likelion.loco.dto.CommentRes;
import com.likelion.loco.entities.Comment;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.repository.CommentRepository;
import com.likelion.loco.repository.PromotionRepository;
import com.likelion.loco.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    private final PromotionRepository promotionRepository;


    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PromotionRepository promotionRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
    }

    public BaseResponseStatus  createComment(Long userIdx, Long promotionIdx,CommentReq.commentCreateReq commentCreateReq){
        try{
            commentRepository.save(commentCreateReq.toEntity(userRepository.findByUserIdx(userIdx).get(),promotionRepository.findPromotionByPromotionIdx(promotionIdx).get()));
            return BaseResponseStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
