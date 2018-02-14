package com.mongodbconnection.demo.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.persistence.Id;


public class Data {

    //@Id
    private String id;
    private Images images;

    public Data() {
    }

    public Data(String id, Images images) {
        this.id = id;
        this.images = images;
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
