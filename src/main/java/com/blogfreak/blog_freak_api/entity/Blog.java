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
import lombok.*;

@Entity
@Table(name = Constant.BLOGS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    @Column(name = Constant.CREATED_BY_USER, insertable = false, updatable = false)
    private String createdByUser;

    @Column(name = Constant.CONTENT)
    private String content;

    @Column(name = Constant.URL_ATTACHMENT)
    private String urlAttachment;

    @Column(name = Constant.CREATED_AT)
    private Date createdAt;

    @Column(name = Constant.UPDATED_AT)
    private Date updatedAt;

    @ManyToMany(
            cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.EAGER)
    @JoinTable(
            name = Constant.BLOGS_MAP_CATEGORIES,
            joinColumns = @JoinColumn(name = Constant.BLOG_ID),
            inverseJoinColumns = @JoinColumn(name = Constant.CATEGORY_ID))
    private List<Category> listOfCategories;
}
