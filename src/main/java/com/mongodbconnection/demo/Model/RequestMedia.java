package com.mongodbconnection.demo.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;

public class RequestMedia {


    private List<Data> data;
    private HttpStatus meta;
    private Pagination pagination;

    public RequestMedia() {
    }

    public RequestMedia(List<Data> data, HttpStatus meta, Pagination pagination) {
        this.data = data;
        this.meta = meta;
        this.pagination = pagination;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public HttpStatus getMeta() {
        return meta;
    }

    public void setMeta(HttpStatus meta) {
        this.meta = meta;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
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
