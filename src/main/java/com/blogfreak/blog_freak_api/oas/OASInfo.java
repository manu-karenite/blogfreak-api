package com.blogfreak.blog_freak_api.oas;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

public class OASInfo {
    private Contact getContactDetails() {
        Contact contact = new Contact();
        contact.setName("Manavesh Narendra");
        contact.setEmail("manavesh.narendra@gmail.com");
        contact.setUrl("manavesh.narendra@gmail.com");
        return contact;
    }

    public Info renderOASInfo() {
        Info info = new Info();
        info.setTitle("Blog-Freak API Documentation");
        info.setVersion("v1");
        info.setContact(getContactDetails());
        return info;
    }
}
