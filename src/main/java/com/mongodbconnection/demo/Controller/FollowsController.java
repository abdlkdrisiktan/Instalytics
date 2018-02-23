package com.mongodbconnection.demo.Controller;

import com.mongodbconnection.demo.Model.Follows;
import com.mongodbconnection.demo.Model.FollowsData;
import com.mongodbconnection.demo.Service.FollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("follows")
public class FollowsController {

    @Autowired
    FollowsService followsService;

    @RequestMapping(value = "getAllFollowsUser",method = RequestMethod.GET)
    @ResponseBody
    public List<FollowsData> getAllFollowsUser (@RequestParam(value = "userId") String userId){
        return  followsService.getAllFollowsUser(userId);
    }
    @RequestMapping(value = "deleteUserById",method = RequestMethod.GET)
    @ResponseBody
    public String deleteUserById (@RequestParam(value = "userId")String userId,@RequestParam(value = "requestId")String requestId){
        return followsService.deleteUserById(userId,requestId);
    }
}
