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
import java.util.Objects;

@Entity
@Table(name = Constant.BLOGGERS)
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

    @Column(name = Constant.PASSWORD)
    private String password;

    @Column(name = Constant.REGISTERED_AT)
    private Date registeredAt;

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

    public Blogger() {
        this.registeredAt = new Date();
        this.lastName = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Blogger blogger = (Blogger) o;
        return Objects.equals(id, blogger.id)
                && Objects.equals(firstName, blogger.firstName)
                && Objects.equals(lastName, blogger.lastName)
                && Objects.equals(emailId, blogger.emailId)
                && Objects.equals(gender, blogger.gender)
                && Objects.equals(password, blogger.password)
                && Objects.equals(registeredAt, blogger.registeredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, emailId, gender, password, registeredAt);
    }

    public Blogger(String id, String firstName, String lastName, String emailId, String gender, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = "";
        this.emailId = emailId;
        this.gender = gender;
        this.password = password;
        this.registeredAt = new Date();
    }

    @Override
    public String toString() {
        return "Blogger{" + "id='"
                + id + '\'' + ", firstName='"
                + firstName + '\'' + ", lastName='"
                + lastName + '\'' + ", emailId='"
                + emailId + '\'' + ", gender='"
                + gender + '\'' + ", password='"
                + registeredAt + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public List<Blog> getListOfBlogsForBlogger() {
        return listOfBlogsForBlogger;
    }

    public void setListOfBlogsForBlogger(List<Blog> listOfBlogsForBlogger) {
        this.listOfBlogsForBlogger = listOfBlogsForBlogger;
    }
}
