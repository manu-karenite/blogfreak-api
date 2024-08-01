package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.dto.CreateCategoryDTO;
import com.blogfreak.blog_freak_api.dto.UpdateCategoryDTO;
import com.blogfreak.blog_freak_api.entity.Category;
import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();

    public Category createCategory(CreateCategoryDTO createCategoryDTORequest);

    public Category deleteCategory(String categoryId);

    public Category getCategoryById(String categoryId);

    public Category updateCategory(String categoryId, UpdateCategoryDTO updateCategoryDTO);
}
