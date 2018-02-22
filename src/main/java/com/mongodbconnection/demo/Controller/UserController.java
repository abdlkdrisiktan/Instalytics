package com.mongodbconnection.demo.Controller;

import com.mongodbconnection.demo.Model.CognitiveServiceMediaStatus;
import com.mongodbconnection.demo.Model.MaxHappinesValues;
import com.mongodbconnection.demo.Model.User;
import com.mongodbconnection.demo.Service.AsyncServicees;
import com.mongodbconnection.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@SuppressWarnings("ALL")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

/*

    @RequestMapping(method = RequestMethod.GET,value = "getUserAccesTokenFromId")
    @ResponseBody
    public MaxHappinesValues getUserAccesTokenFromId(@RequestParam(value = "userId") String userId, @RequestParam(value = "userRequestId")String userRequestId ){
        return userService.getUserAccesTokenFromId(userId,userRequestId,null,null,null);
    }
    */
    @RequestMapping(method =  RequestMethod.GET,value = "getUserAccesTokenFromId")
    @ResponseBody
    public CognitiveServiceMediaStatus getStatusIsDone(@RequestParam(value = "userId") String userId, @RequestParam(value = "userRequestId")String userRequestId){
        return userService.getStatusIsDone(userId,userRequestId);
    }

}

