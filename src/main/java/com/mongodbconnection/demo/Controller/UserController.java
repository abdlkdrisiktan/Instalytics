package com.mongodbconnection.demo.Controller;

import com.mongodbconnection.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.GET,value = "getUserAccesTokenFromId")
    public String getUserAccesTokenFromId(@RequestParam(value = "userId") String userId,@RequestParam(value = "userRequestId")String userRequestId ){
        return userService.getUserAccesTokenFromId(userId,userRequestId);
    }

}

