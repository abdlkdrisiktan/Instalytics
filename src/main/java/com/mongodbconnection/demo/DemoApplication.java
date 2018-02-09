package com.mongodbconnection.demo;

import com.mongodbconnection.demo.Entity.Media;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.io.IOException;


@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);

	}





}
