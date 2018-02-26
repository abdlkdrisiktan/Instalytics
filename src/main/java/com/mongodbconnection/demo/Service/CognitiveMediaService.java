package com.mongodbconnection.demo.Service;

import com.mongodbconnection.demo.Model.CognitiveServiceMediaStatus;
import com.mongodbconnection.demo.Repository.CognitiveServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CognitiveMediaService {

    @Autowired
    CognitiveServiceRepository cognitiveServiceRepository;

    public String deleteUserById(String userId,String requestId){
        System.out.println((userId+requestId));
        String totalId = (userId+requestId);
        cognitiveServiceRepository.delete(totalId);
        return "deleted";
    }
    public List<CognitiveServiceMediaStatus> getAllCognitiveServiceMedia(){
        List<CognitiveServiceMediaStatus> cognitiveServiceMediaStatus = cognitiveServiceRepository.findAll();
        return cognitiveServiceMediaStatus;
    }
}
