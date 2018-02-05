package com.mongodbconnection.demo.Repository;

import com.mongodbconnection.demo.Entity.Media;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface MediaRepository extends MongoRepository<Media,String>,PagingAndSortingRepository<Media,String> {
}
