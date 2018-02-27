package com.mongodbconnection.demo.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "CognitiveServiceMediaStatus")
public class CognitiveServiceMediaStatus {

    private String id;
    private String status;
    private List<MaxHappinesValues> maxHappinesValues;

    public CognitiveServiceMediaStatus(String id, String status, List<MaxHappinesValues> maxHappinesValues) {
        this.id = id;
        this.status = status;
        this.maxHappinesValues = maxHappinesValues;
    }

    public List<MaxHappinesValues> getMaxHappinesValues() {
        return maxHappinesValues;
    }

    public void setMaxHappinesValues(List<MaxHappinesValues> maxHappinesValues) {
        this.maxHappinesValues = maxHappinesValues;
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

    public CognitiveServiceMediaStatus() {
    }
}
