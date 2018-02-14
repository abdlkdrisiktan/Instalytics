package com.mongodbconnection.demo.Repository;

import com.mongodbconnection.demo.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findById (String id);

}
