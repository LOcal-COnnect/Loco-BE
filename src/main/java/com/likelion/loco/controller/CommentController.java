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

import java.util.List;

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
    public ResponseEntity<BaseResponseStatus> commentCreate(@AuthenticationPrincipal String userId, @PathVariable("promotionIdx") Long promotionIdx, @PathVariable("userIdx") Long userIdx, @RequestBody CommentReq.commentCreateReq commentCreateReq) {
        try {
            System.out.println("userId : " + userId);

            BaseResponseStatus responseStatus = commentService.createComment(tokenProvider.extractRoleFromBearerToken(userId), userIdx, promotionIdx, commentCreateReq);
            if (responseStatus != null && responseStatus.equals(BaseResponseStatus.SUCCESS)) {
                return new ResponseEntity<>(responseStatus, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("{commentIdx}")
    public ResponseEntity<BaseResponseStatus> commentUpdate(@AuthenticationPrincipal String userId, @PathVariable("commentIdx") Long commentIdx, @RequestBody CommentReq.commentUpdateReq commentUpdateReq) {
        try {
            BaseResponseStatus responseStatus = commentService.updateComment(userId, commentIdx, commentUpdateReq);
            if (responseStatus != null && responseStatus.equals(BaseResponseStatus.SUCCESS)) {
                return new ResponseEntity<>(responseStatus, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/promotion/{promotionIdx}")
    public ResponseEntity<List<CommentRes.CommentListRes>> getAllCommentsByPromotionIdx(@PathVariable("promotionIdx") Long promotionIdx) {
        try {
            List<CommentRes.CommentListRes> comments = commentService.getCommentByPromotionIdx(promotionIdx);
            if (comments != null && !comments.isEmpty()) {
                return new ResponseEntity<>(comments, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{commentIdx}")
    public ResponseEntity<HttpStatus> deleteMyComment(@PathVariable("commentIdx") Long commentIdx) {
        try {
            if (commentService.deleteByCommentIdx(commentIdx)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
