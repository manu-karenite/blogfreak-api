package com.blogfreak.blog_freak_api.dao;

import com.blogfreak.blog_freak_api.entity.Category;
import java.util.List;

public interface CategoryDAO {
    public List<Category> getAllCategories();

    public Category createCategory(Category category);

    public Category deleteCategory(String categoryId);

    public Category getCategoryById(String categoryId);

    public Category updateCategory(Category category);
}
