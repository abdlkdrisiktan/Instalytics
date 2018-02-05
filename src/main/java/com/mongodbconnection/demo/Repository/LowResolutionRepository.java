package com.mongodbconnection.demo.Repository;

import com.mongodbconnection.demo.Entity.LowResolution;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LowResolutionRepository extends MongoRepository<LowResolution,String> {

}
