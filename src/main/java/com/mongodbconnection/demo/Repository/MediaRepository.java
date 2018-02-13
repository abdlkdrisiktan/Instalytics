package com.mongodbconnection.demo.Repository;

import com.mongodbconnection.demo.Model.Media;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface MediaRepository extends MongoRepository<Media,String>,PagingAndSortingRepository<Media,String> {

   /* @Query(value ="{'_id'   :   ?0 )", fields = "{'_id' : 1688264887456000716_1386731221 }")
    List<Media> findByMediaId(String mediaId);*/

   //Finding by id to user and getting face emotion
   Media findById(String id);

   //Finding by ownerId and getting happiest moment
   List<Media> findByMediaOwner(String mediaOwner);


}
