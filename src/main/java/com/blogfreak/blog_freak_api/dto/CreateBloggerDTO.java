package com.blogfreak.blog_freak_api.dto;

import com.blogfreak.blog_freak_api.validators.Gender.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateBloggerDTO {
    @NotNull(message = "firstName is missing")
    @Size(min = 2, message = "firsName must contain 2 or more letters")
    private String firstName;

    private String lastName;

    @NotNull(message = "emailId is missing")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String emailId;

    @NotNull
    @Gender
    private String gender;

    @NotNull(message = "password is missing")
    @Size(min = 6, message = "password must contain 6 or more letters")
    private String password;

    public CreateBloggerDTO() {}

    public CreateBloggerDTO(String firstName, String lastName, String emailId, String gender, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.gender = gender;
        this.password = password;
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
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
