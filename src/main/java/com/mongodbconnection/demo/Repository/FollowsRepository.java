package com.mongodbconnection.demo.Repository;

import com.mongodbconnection.demo.Model.Follows;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FollowsRepository extends MongoRepository<Follows,String> {

}
