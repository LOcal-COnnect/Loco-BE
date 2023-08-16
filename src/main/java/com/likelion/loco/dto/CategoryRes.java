package com.likelion.loco.dto;

import com.likelion.loco.entities.Category;
import com.likelion.loco.entities.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CategoryRes {
    @Getter
    @Setter
    public static class CategoryListRes{
        private List<Category> categoryList;

        public CategoryListRes(List<Category> categoryList) {
            this.categoryList = categoryList;
        }
    }

}
