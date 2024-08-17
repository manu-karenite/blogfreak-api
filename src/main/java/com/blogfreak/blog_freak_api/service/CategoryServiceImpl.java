package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.dao.CategoryDAO;
import com.blogfreak.blog_freak_api.dto.CreateCategoryDTO;
import com.blogfreak.blog_freak_api.dto.UpdateCategoryDTO;
import com.blogfreak.blog_freak_api.entity.Category;
import com.blogfreak.blog_freak_api.util.StringUtility;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Transactional
    @Override
    public Category createCategory(CreateCategoryDTO createCategoryDTORequest) {
        Category category = new Category();
        category.setName(createCategoryDTORequest.getName());
        category.setId(StringUtility.generateIdForEntity());
        Date currentDT = new Date();
        category.setCreatedAt(currentDT);
        category.setUpdatedAt(currentDT);
        category.setVersion(Integer.valueOf(1));
        return categoryDAO.createCategory(category);
    }

    @Transactional
    @Override
    public Category deleteCategory(String categoryId) {
        return categoryDAO.deleteCategory(categoryId);
    }

    public Category getCategoryById(String categoryId) {
        return categoryDAO.getCategoryById(categoryId);
    }

    @Transactional
    @Override
    public Category updateCategory(String categoryId, UpdateCategoryDTO updateCategoryDTO) {
        Category toBeUpdatedCategory = getCategoryById(categoryId);
        toBeUpdatedCategory.setName(updateCategoryDTO.getName());
        toBeUpdatedCategory.setVersion(
                toBeUpdatedCategory.getVersion() == null ? Integer.valueOf(1) : toBeUpdatedCategory.getVersion() + 1);
        toBeUpdatedCategory.setUpdatedAt(new Date());
        return categoryDAO.updateCategory(toBeUpdatedCategory);
    }
}
