package com.blogfreak.blog_freak_api.entity;

import com.blogfreak.blog_freak_api.util.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @Column(name = Constant.VERSION)
    private Integer version;

    @Column(name = Constant.LIKES_COUNT)
    private Integer likesCount;

    @ManyToMany(
            cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.EAGER)
    @JoinTable(
            name = Constant.BLOGS_MAP_CATEGORIES,
            joinColumns = @JoinColumn(name = Constant.BLOG_ID),
            inverseJoinColumns = @JoinColumn(name = Constant.CATEGORY_ID))
    private List<Category> listOfCategories;

    @JsonIgnore
    @OneToMany(
            mappedBy = "blogId",
            fetch = FetchType.EAGER,
            cascade = {
                CascadeType.REMOVE,
                CascadeType.REFRESH,
            },
            orphanRemoval = false)
    private List<BlogLike> listOfLikesForBlog;
}
