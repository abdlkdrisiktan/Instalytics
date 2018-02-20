package com.mongodbconnection.demo.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "CognitiveServiceMediaStatus")
public class CognitiveServiceMediaStatus {
    private String id;
    private String userRequestId;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserRequestId() {
        return userRequestId;
    }

    public void setUserRequestId(String userRequestId) {
        this.userRequestId = userRequestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CognitiveServiceMediaStatus() {
    }

    public CognitiveServiceMediaStatus(String id, String userRequestId, String status) {
        this.id = id;
        this.userRequestId = userRequestId;
        this.status = status;
    }
}
