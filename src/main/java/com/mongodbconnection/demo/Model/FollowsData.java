package com.mongodbconnection.demo.Model;

public class FollowsData {
    private String id;
    private String username;
    private String profile_picture;
    private String full_name;
    private String state;

    public FollowsData() {
    }

    public FollowsData(String id, String username, String profile_picture, String full_name, String state) {
        this.id = id;
        this.username = username;
        this.profile_picture = profile_picture;
        this.full_name = full_name;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
