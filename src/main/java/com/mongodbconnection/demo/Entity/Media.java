package com.mongodbconnection.demo.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

@Document(collection = "Media")
public class Media {
    
    @Id
    private String images;
    private String users_in_photo;

    public Media() {
    }

    public Media(String images, String users_in_photo) {
        this.images = images;
        this.users_in_photo = users_in_photo;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getUsers_in_photo() {
        return users_in_photo;
    }

    public void setUsers_in_photo(String users_in_photo) {
        this.users_in_photo = users_in_photo;
    }
}
