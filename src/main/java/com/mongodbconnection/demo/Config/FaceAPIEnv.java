package com.mongodbconnection.demo.Config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;



@Configuration
public class FaceAPIEnv {
    @Value("${subscription.key}")
    private String subscriptionKey;

    public String getKey(){
        return subscriptionKey;
    }

}
