package com.blogfreak.blog_freak_api.entity;

import com.blogfreak.blog_freak_api.util.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = Constant.BLOGS)
public class Blog {
    @Id
    @Column(name = Constant.ID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = Constant.CREATED_BY_USER)
    private Blogger blogger;

    @Column(name = Constant.TITLE)
    private String title;

    @Column(name = Constant.CONTENT)
    private String content;

    @Column(name = Constant.URL_ATTACHMENT)
    private String urlAttachment;

    @Column(name = Constant.CREATED_AT)
    private Date createdAt;

    @Column(name = Constant.UPDATED_AT)
    private Date updatedAt;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = Constant.BLOGS_MAP_CATEGORIES,
            joinColumns = @JoinColumn(name = Constant.BLOG_ID),
            inverseJoinColumns = @JoinColumn(name = Constant.CATEGORY_ID))
    private List<Category> listOfCategories;

    public List<Category> getListOfCategories() {
        return listOfCategories;
    }

    public void setListOfCategories(List<Category> listOfCategories) {
        this.listOfCategories = listOfCategories;
    }

    public Blog() {}

    public Blog(String id, String title, String content, String urlAttachment, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.urlAttachment = urlAttachment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlAttachment() {
        return urlAttachment;
    }

    public void setUrlAttachment(String urlAttachment) {
        this.urlAttachment = urlAttachment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Blogger getBlogger() {
        return blogger;
    }

    public void setBlogger(Blogger blogger) {
        this.blogger = blogger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Blog blog = (Blog) o;
        return Objects.equals(id, blog.id)
                && Objects.equals(title, blog.title)
                && Objects.equals(content, blog.content)
                && Objects.equals(urlAttachment, blog.urlAttachment)
                && Objects.equals(createdAt, blog.createdAt)
                && Objects.equals(updatedAt, blog.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, urlAttachment, createdAt, updatedAt);
    }
}
