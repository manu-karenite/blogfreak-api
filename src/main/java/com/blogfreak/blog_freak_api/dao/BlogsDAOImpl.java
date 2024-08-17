package com.blogfreak.blog_freak_api.dao;

import com.blogfreak.blog_freak_api.entity.Blog;
import com.blogfreak.blog_freak_api.exception.BlogNotFound;
import com.blogfreak.blog_freak_api.exception.ForbiddenException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogsDAOImpl implements BlogsDAO {

    @Autowired
    private EntityManager entityManager;

    public BlogsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Blog> getAllBlogs() {
        TypedQuery<Blog> query = entityManager.createQuery("from Blog", Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog getBlogById(String blogId) {
        entityManager.clear();
        Blog blog = entityManager.find(Blog.class, blogId);
        if (blog == null) throw new BlogNotFound(String.format("Blog with id %s not found", blogId));
        return blog;
    }

    @Override
    public Blog createBlog(Blog blog) {
        this.entityManager.persist(blog);
        return blog;
    }

    @Override
    public Blog deleteBlogByblogId(String blogId, final String bloggerId) {
        Blog blog = getBlogById(blogId);
        if (!blog.getBlogger().getId().equalsIgnoreCase(bloggerId)) {
            throw new ForbiddenException(String.format(
                    "Blog with blogId [%s] does not belong to blogger with id : [%s]", blogId, bloggerId));
        }
        this.entityManager.remove(blog);
        return blog;
    }

    @Override
    public Blog updateBlogByblogId(final Blog tobeUpdatedBlog, final String bloggerId) {
        String toBeUpdatedBlogBloggerId = tobeUpdatedBlog.getBlogger().getId();
        if (!toBeUpdatedBlogBloggerId.equalsIgnoreCase(bloggerId)) {
            throw new ForbiddenException(String.format(
                    "Blog with blogId [%s] does not belong to blogger with id : [%s]",
                    toBeUpdatedBlogBloggerId, bloggerId));
        }
        // Remove the existing categories mappings from the BlogMapCategory Entity
        Query query = entityManager.createQuery("DELETE FROM BlogMapCategory bmc WHERE bmc.blogId=:blogId");
        query.setParameter("blogId", tobeUpdatedBlog.getId());
        query.executeUpdate();
        // Merge the new categories mappings to the BlogMapCategory Entity
        this.entityManager.merge(tobeUpdatedBlog);
        return tobeUpdatedBlog;
    }

    public List<Blog> getListOfAllBlogsInListOfCategories(final Set<String> categoryIdsSet) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT DISTINCT bmc.blogId FROM BlogMapCategory bmc WHERE bmc.categoryId IN(:categoryIdsSet)",
                String.class);
        query.setParameter("categoryIdsSet", categoryIdsSet);
        List<String> returnedBlogList = query.getResultList();
        final List<Blog> blogList = new ArrayList<>();
        for (String blogId : returnedBlogList) blogList.add(getBlogById(blogId));
        return blogList;
    }
}
