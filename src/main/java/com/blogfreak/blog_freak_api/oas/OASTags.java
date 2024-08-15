package com.blogfreak.blog_freak_api.oas;

import io.swagger.v3.oas.models.tags.Tag;
import java.util.ArrayList;
import java.util.List;

public class OASTags {
    public List<Tag> getOASTags() {
        Tag tag0 = new Tag();
        tag0.setName("Authentication");
        tag0.setDescription("API Endpoints related to authentication");

        Tag tag1 = new Tag();
        tag1.setName("Bloggers");
        tag1.setDescription("API Endpoints related to bloggers");

        Tag tag2 = new Tag();
        tag2.setName("Categories");
        tag2.setDescription("API Endpoints related to categories");

        Tag tag3 = new Tag();
        tag3.setName("Blogs");
        tag3.setDescription("API Endpoints related to blogs");

        Tag tag4 = new Tag();
        tag4.setName("Health Check");
        tag4.setDescription("API Endpoints related to service health check");

        List<Tag> listOfTags = new ArrayList<>();
        listOfTags.add(tag0);
        listOfTags.add(tag1);
        listOfTags.add(tag2);
        listOfTags.add(tag3);
        listOfTags.add(tag4);
        return listOfTags;
    }
}
