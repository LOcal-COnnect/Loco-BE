package com.likelion.loco.service;

import com.likelion.loco.dto.CategoryRes;
import com.likelion.loco.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryRes.CategoryListRes getAllCategoryList(){
        return new CategoryRes.CategoryListRes(categoryRepository.findAll());

    }
}
