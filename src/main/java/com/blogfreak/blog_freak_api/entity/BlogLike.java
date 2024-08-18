package com.blogfreak.blog_freak_api.entity;

import com.blogfreak.blog_freak_api.util.Constant;
import jakarta.persistence.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "blog_likes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogLike {

    @Id
    @Column(name = Constant.ID)
    private String id;

    @Column(name = Constant.BLOG_ID)
    String blogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constant.BLOGGER_ID)
    Blogger blogger;

    @Column(name = Constant.CREATED_AT)
    Date createdAt;
}
