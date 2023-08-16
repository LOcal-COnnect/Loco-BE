package com.likelion.loco.controller;

import com.likelion.loco.dto.CategoryRes;
import com.likelion.loco.service.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getall")
    public CategoryRes.CategoryListRes getAllCategory(){
        try{
            return categoryService.getAllCategoryList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

}
