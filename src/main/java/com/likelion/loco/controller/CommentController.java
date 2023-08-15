package com.likelion.loco.controller;

import com.likelion.loco.dto.CommentReq;
import com.likelion.loco.dto.CommentRes;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.jwt.TokenProvider;
import com.likelion.loco.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final TokenProvider tokenProvider;

    public CommentController(CommentService commentService, TokenProvider tokenProvider) {
        this.commentService = commentService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/{promotionIdx}/users/{userIdx}")
    public BaseResponseStatus commentCreate(@AuthenticationPrincipal String userId, @PathVariable("promotionIdx") Long promotionIdx, @PathVariable("userIdx") Long userIdx, @RequestBody CommentReq.commentCreateReq commentCreateReq){
        try{
            System.out.println("userId : "+userId);

            return commentService.createComment(tokenProvider.extractRoleFromBearerToken(userId),userIdx,promotionIdx,commentCreateReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/promotion/{promotionIdx}")
    public CommentRes.CommentListRes getAllCommentsByPromotionIdx(@PathVariable("promotionIdx") Long promotionIdx){
        try{
            return commentService.getCommentByPromotionIdx(promotionIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/{commentIdx}")
    public ResponseEntity<HttpStatus> deleteMyComment(@PathVariable("commentIdx") Long commentIdx){
        try{
            commentService.deleteByCommentIdx(commentIdx);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
