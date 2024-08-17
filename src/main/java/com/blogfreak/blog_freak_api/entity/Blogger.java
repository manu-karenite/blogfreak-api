package com.blogfreak.blog_freak_api.entity;

import com.blogfreak.blog_freak_api.util.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = Constant.BLOGGERS)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Blogger {
    @Id
    @Column(name = Constant.ID)
    private String id;

    @Column(name = Constant.FIRST_NAME)
    private String firstName;

    @Column(name = Constant.LAST_NAME)
    private String lastName;

    @Column(name = Constant.EMAIL_ID)
    private String emailId;

    @Column(name = Constant.GENDER)
    private String gender;

    @Column(name = Constant.VERSION)
    private Integer version;

    @JsonIgnore
    @Column(name = Constant.PASSWORD)
    private String password;

    @Column(name = Constant.REGISTERED_AT)
    private Date registeredAt;

    @Column(name = Constant.UPDATED_AT)
    private Date updatedAt;

    @OneToMany(
            mappedBy = Constant.BLOGGER,
            fetch = FetchType.EAGER,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE,
                CascadeType.REMOVE,
                CascadeType.REFRESH,
                CascadeType.REFRESH
            },
            orphanRemoval = false)
    private List<Authority> listOfAuthorities;

    // One to Many Mapping with Blog Entity
    @OneToMany(
            mappedBy = Constant.BLOGGER,
            fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE,
                CascadeType.REMOVE,
                CascadeType.REFRESH,
                CascadeType.REFRESH
            },
            orphanRemoval = false)
    @JsonIgnore
    public List<Blog> listOfBlogsForBlogger;
}
