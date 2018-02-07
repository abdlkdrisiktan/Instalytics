package com.mongodbconnection.demo.Model;

public class FaceAPIModel {

    private String key;
    private String url;
    private String attributes;

    public FaceAPIModel() {
    }

    public FaceAPIModel(String key, String url, String attributes) {
        this.key = key;
        this.url = url;
        this.attributes = attributes;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
}
