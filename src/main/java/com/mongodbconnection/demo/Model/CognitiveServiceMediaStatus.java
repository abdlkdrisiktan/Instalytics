package com.mongodbconnection.demo.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "CognitiveServiceMediaStatus")
public class CognitiveServiceMediaStatus {

    private String id;
    private String status;
    private String maxHappinesValueUrl;
    private String maxHappinesValue;

    public CognitiveServiceMediaStatus(String id, String status, String maxHappinesValueUrl, String maxHappinesValue) {
        this.id = id;
        this.status = status;
        this.maxHappinesValueUrl = maxHappinesValueUrl;
        this.maxHappinesValue = maxHappinesValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaxHappinesValueUrl() {
        return maxHappinesValueUrl;
    }

    public void setMaxHappinesValueUrl(String maxHappinesValueUrl) {
        this.maxHappinesValueUrl = maxHappinesValueUrl;
    }

    public String getMaxHappinesValue() {
        return maxHappinesValue;
    }

    public void setMaxHappinesValue(String maxHappinesValue) {
        this.maxHappinesValue = maxHappinesValue;
    }

    public CognitiveServiceMediaStatus() {
    }
}
