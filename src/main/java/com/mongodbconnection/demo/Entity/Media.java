package com.mongodbconnection.demo.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

@Document(collection = "Media")
public class Media {

    private LowResolution low_resolution;
    private String users_in_photo;

    public Media(LowResolution low_resolution, String users_in_photo) {
        this.low_resolution = low_resolution;
        this.users_in_photo = users_in_photo;
    }

    public Media() {
    }

    public LowResolution getLow_resolution() {
        return low_resolution;
    }

    public void setLow_resolution(LowResolution low_resolution) {
        this.low_resolution = low_resolution;
    }

    public String getUsers_in_photo() {
        return users_in_photo;
    }

    public void setUsers_in_photo(String users_in_photo) {
        this.users_in_photo = users_in_photo;
    }
}
