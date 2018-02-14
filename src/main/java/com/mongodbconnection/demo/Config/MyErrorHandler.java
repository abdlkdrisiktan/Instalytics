package com.mongodbconnection.demo.Config;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class MyErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        System.out.println(clientHttpResponse.getStatusText()+" "+clientHttpResponse.getStatusCode()+"  "+clientHttpResponse.getRawStatusCode());
        if (clientHttpResponse.getStatusText().contains("you cannot view this resource")){
            return false;
        }
        return true;
    }
    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

    }
}
