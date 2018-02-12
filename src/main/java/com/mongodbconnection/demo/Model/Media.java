package com.mongodbconnection.demo.Model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.Id;
import java.util.List;


@Document(collection = "Media")
public class Media {

    @Id
    private String id;

    private Images images;
    private List<String> users_in_photo;
    private String mediaOwner;

    public Media(String id, Images images, List<String> users_in_photo, String mediaOwner) {
        this.id = id;
        this.images = images;
        this.users_in_photo = users_in_photo;
        this.mediaOwner = mediaOwner;
    }

    public Media() {
    }

    public String getMediaOwner() {
        return mediaOwner;
    }

    public void setMediaOwner(String mediaOwner) {
        this.mediaOwner = mediaOwner;
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

    public void setUsers_in_photo(List<String> users_in_photo) {
        this.users_in_photo = users_in_photo;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonString=mapper.writeValueAsString(this);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
