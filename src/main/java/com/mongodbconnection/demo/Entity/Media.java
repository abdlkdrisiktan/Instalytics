package com.mongodbconnection.demo.Entity;


import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.Id;
import java.util.List;


@Document(collection = "Media")
public class Media {

    private Images images;
    private List users_in_photo;


    public Media() {
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public List getUsers_in_photo() {
        return users_in_photo;
    }

    public void setUsers_in_photo(List users_in_photo) {
        this.users_in_photo = users_in_photo;
    }
    /*public String getUsers_in_photo() {
        return users_in_photo;
    }

    public void setUsers_in_photo(String users_in_photo) {
        this.users_in_photo = users_in_photo;
    }*/


}
