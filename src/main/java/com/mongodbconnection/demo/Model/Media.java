package com.mongodbconnection.demo.Model;


import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.Id;
import java.util.List;


@Document(collection = "Media")
public class Media {

    @Id
    private String id;

    private Images images;
    private List users_in_photo;

    public Media(String id, Images images, List users_in_photo) {
        this.id = id;
        this.images = images;
        this.users_in_photo = users_in_photo;

    }

    public Media() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
