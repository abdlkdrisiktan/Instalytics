package com.mongodbconnection.demo.Service;

import com.mongodbconnection.demo.Model.Follows;
import com.mongodbconnection.demo.Model.FollowsData;
import com.mongodbconnection.demo.Repository.FollowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowsService {


    @Autowired
    FollowsRepository followsRepository;

    public List<FollowsData> getAllFollowsUser(String id){
        Follows follows = followsRepository.findOne(id);
        List<FollowsData> followsData = new ArrayList<>();
        followsData = follows.getData();
        return followsData;
    }
    public String deleteUserById(String userId,String requestId){
        followsRepository.delete((userId+requestId));
        return "deleted";
    }

}
