package com.likelion.loco.dto;

import com.likelion.loco.entities.Comment;
import com.likelion.loco.entities.Store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentRes {
    private Long commentIdx;
    private String commentContent;
    private LocalDateTime createdAt;

    public CommentRes(String commentContent) {
        this.commentContent=commentContent;
    }
    

    @Getter
    @Setter
    public static class CommentListRes{
        private Comment comment;
        private String userName;

        public CommentListRes(Comment comment) {
            this.comment = comment;
            this.userName = comment.getUser().getUserName();
        }
    }

}
