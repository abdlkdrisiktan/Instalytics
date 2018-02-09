package com.mongodbconnection.demo.Repository;

import com.mongodbconnection.demo.Entity.Media;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface MediaRepository extends MongoRepository<Media,String>,PagingAndSortingRepository<Media,String> {

   /* @Query(value ="{'_id'   :   ?0 )", fields = "{'_id' : 1688264887456000716_1386731221 }")
    List<Media> findByMediaId(String mediaId);*/

  List<Media> findByMediaId(String mediaId);


}
