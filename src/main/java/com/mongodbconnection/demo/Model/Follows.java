package com.mongodbconnection.demo.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Follows")
public class Follows {
    private String id;
    private String updateStatus;
    private List<FollowsData> data;

    public Follows() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public List<FollowsData> getData() {
        return data;
    }

    public void setData(List<FollowsData> data) {
        this.data = data;
    }

    public Follows(String id, String updateStatus, List<FollowsData> data) {
        this.id = id;
        this.updateStatus = updateStatus;
        this.data = data;
    }
}
