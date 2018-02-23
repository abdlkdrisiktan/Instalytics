package com.mongodbconnection.demo.Service;


import com.mongodbconnection.demo.Model.*;
import com.mongodbconnection.demo.Repository.CognitiveServiceRepository;
import com.mongodbconnection.demo.Repository.FollowsRepository;
import com.mongodbconnection.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


@SuppressWarnings("ALL")
@Service
@EnableScheduling
public class UserService {

    @Autowired
    CognitiveServiceRepository cognitiveServiceRepository;

    @Autowired
    AsyncServicees asyncService;

    @Autowired
    FollowsRepository followsRepository;

    public CognitiveServiceMediaStatus getStatusIsDone(String userId, String userRequestId) {

        System.out.println("cognitive ");
        String totalId = userId + userRequestId;
        CognitiveServiceMediaStatus cognitiveServiceMediaStatus = cognitiveServiceRepository.findOne(totalId);
//        if (cognitiveServiceMediaStatus!=null && cognitiveServiceMediaStatus.getStatus()!=null && cognitiveServiceMediaStatus.getStatus().equals("Updating")){
//            cognitiveServiceRepository.save(cognitiveServiceMediaStatus);
//            asyncService.getUserAccesTokenFromId(userId,userRequestId,null,null,null);
//        }
        if (cognitiveServiceMediaStatus != null && cognitiveServiceMediaStatus.getStatus() != null && cognitiveServiceMediaStatus.getStatus().equals("Done")) {
            System.out.println("line 49 if");
            return cognitiveServiceMediaStatus;
        } else {
            if (cognitiveServiceMediaStatus == null ) {
                cognitiveServiceMediaStatus = new CognitiveServiceMediaStatus();
                cognitiveServiceMediaStatus.setId(totalId);
                cognitiveServiceMediaStatus.setStatus("Updating");
                cognitiveServiceRepository.save(cognitiveServiceMediaStatus);
                System.out.println("DENEME");
                asyncService.getUserAccesTokenFromId(userId, userRequestId, null, null, null);
            }
            System.out.println("before return");
            return cognitiveServiceMediaStatus;
        }

    }
}
//örnek id 5261988411 veya 1931815659
//örnek id  32 size 1083659435
//ilk once veritabnına gidip status  updates olup olmadığını kontrol edecegız dahasonrasından aseknron olarak istek yapacak
//ıkı ıd veri tabanına kaydet

