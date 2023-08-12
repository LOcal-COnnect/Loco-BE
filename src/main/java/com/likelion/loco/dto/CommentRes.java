package com.likelion.loco.dto;

import com.likelion.loco.entities.Comment;
import com.likelion.loco.entities.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class CommentRes {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class CommentListRes{
        private List<Comment> commentList;
    }

}