package com.blogfreak.blog_freak_api.dao;

import com.blogfreak.blog_freak_api.entity.Category;
import com.blogfreak.blog_freak_api.exception.CategoryNotFound;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    @Autowired
    EntityManager entityManager;

    public CategoryDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Category> getAllCategories() {
        TypedQuery<Category> query = entityManager.createQuery("from Category", Category.class);
        List<Category> listOfAllCategories = query.getResultList();
        return listOfAllCategories;
    }

    @Override
    public Category createCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    @Override
    public Category deleteCategory(String categoryId) {
        Category category = entityManager.find(Category.class, categoryId);
        if (category == null)
            throw new CategoryNotFound(String.format("Category with id : [%s] does not exist", categoryId));
        entityManager.remove(category);
        return category;
    }

    @Override
    public Category getCategoryById(String categoryId) {
        this.entityManager.clear();
        Category category = entityManager.find(Category.class, categoryId);
        if (category == null)
            throw new CategoryNotFound(String.format("Category with id : [%s] does not exist", categoryId));
        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        entityManager.merge(category);
        return category;
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        TypedQuery<Category> query = this.entityManager.createNamedQuery("Category.findByName", Category.class);
        query.setParameter("categoryName", categoryName);
        List<Category> categoryList = query.getResultList();
        if (categoryList == null || categoryList.size() == 0) return null;
        return categoryList.get(0);
    }
}
