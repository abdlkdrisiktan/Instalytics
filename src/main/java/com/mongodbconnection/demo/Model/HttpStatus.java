package com.mongodbconnection.demo.Model;

import org.springframework.data.mongodb.core.mapping.Document;


public class HttpStatus {

    private int code;

    private String error_type;

    private String  error_message;

    public HttpStatus(int code, String error_type, String error_message) {
        this.code = code;
        this.error_type = error_type;
        this.error_message = error_message;
    }

    public HttpStatus() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError_type() {
        return error_type;
    }

    public void setError_type(String error_type) {
        this.error_type = error_type;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
