package com.mongodbconnection.demo.Entity;


import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.Id;
import java.util.List;


@Document(collection = "Media")
public class Media {

    @Id
    private String mediaId;
    private Images images;
    private List users_in_photo;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Media(String mediaId, Images images, List users_in_photo) {
        this.mediaId = mediaId;
        this.images = images;
        this.users_in_photo = users_in_photo;
    }

    public Media() {
    }
    public Media(String mediaId) {
        this.mediaId= mediaId;
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
