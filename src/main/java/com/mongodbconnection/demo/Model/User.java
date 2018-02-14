package com.mongodbconnection.demo.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Document(collection = "Users")
public class User {

    @Id
    private String id;
    private String access_token;
    private String fcmToken;
    private String tempId;
    private String username;


    public User() {
    }

    public User(String id, String access_token, String fcmToken, String tempId, String username) {
        this.id = id;
        this.access_token = access_token;
        this.fcmToken = fcmToken;
        this.tempId = tempId;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
