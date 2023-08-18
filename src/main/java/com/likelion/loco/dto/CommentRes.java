package com.likelion.loco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    // ... (생성자, 메소드 등)
}
