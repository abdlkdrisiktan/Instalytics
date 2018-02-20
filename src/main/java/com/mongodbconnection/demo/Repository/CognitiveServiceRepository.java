package com.mongodbconnection.demo.Repository;

import com.mongodbconnection.demo.Model.CognitiveServiceMediaStatus;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CognitiveServiceRepository extends MongoRepository<CognitiveServiceMediaStatus,String> {


}
