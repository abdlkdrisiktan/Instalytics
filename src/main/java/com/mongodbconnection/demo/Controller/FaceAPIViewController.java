package com.mongodbconnection.demo.Controller;

import com.mongodbconnection.demo.Config.FaceAPIEnv;
import com.mongodbconnection.demo.Model.FaceAPIModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaceAPIViewController {
    @Autowired
    private FaceAPIEnv faceAPIEnv;

    @GetMapping("/")
    public String init(Model model){
        FaceAPIModel faceAPIModel = new FaceAPIModel();
        faceAPIModel.setKey(faceAPIEnv.getKey());
        model.addAttribute("model",faceAPIModel);
        return "detect";
    }
}
