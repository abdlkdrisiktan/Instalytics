package com.mongodbconnection.demo.Controller;


import com.mongodbconnection.demo.Model.CognitiveServiceMediaStatus;
import com.mongodbconnection.demo.Service.CognitiveMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cognitiveMediaService")
public class CognitiveMediaController {

    @Autowired
    CognitiveMediaService cognitiveMediaService;

    @RequestMapping(value = "deleteUserById",method = RequestMethod.GET)
    @ResponseBody
    public String deleteUserById (@RequestParam(value = "userId")String userId,@RequestParam(value = "requestId")String requestId){
        return cognitiveMediaService.deleteUserById(userId,requestId);
    }

    @RequestMapping(value = "getAllCognitiveServiceMedia", method = RequestMethod.GET)
    @ResponseBody
    public List<CognitiveServiceMediaStatus> getAllCognitiveServiceMedia(){
        return cognitiveMediaService.getAllCognitiveServiceMedia();
    }
}
