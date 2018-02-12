package com.mongodbconnection.demo.Controller;


import com.mongodbconnection.demo.Config.FaceAPIEnv;
import com.mongodbconnection.demo.Model.FaceAPIModel;
import com.mongodbconnection.demo.Model.Media;
import com.mongodbconnection.demo.Service.MediaServices;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import sun.rmi.runtime.Log;

@Controller
@RestController
@RequestMapping("media")
public class MediaController {

    @Autowired
    MediaServices mediaServices;

    @Autowired
    FaceAPIEnv faceAPIEnv;

    @Value("${face.attributes}")
    private String faceAttributes;

    public FaceAPIModel faceAPIModel;
/*
    @RequestMapping(method = RequestMethod.GET, value = "/isExistMedia")
    @ResponseBody
    public Media isMediaExist(@RequestParam(value = "id") String id){
      /*  id= "1688264887456000716_1386731221";
        Media media =mediaServices.isMediaExist(id);
        System.out.println(media.getId()+"      "+ media.getUsers_in_photo().size()+"        "+media.getImages().getLow_resolution().getUrl());
       // checkMediaEmotion(faceAPIModel,media);
       // System.out.println(checkMediaEmotion(faceAPIModel,media));
        //System.out.println();
        return mediaServices.detectMediaEmotion(id);


    }
    */

    @RequestMapping(method =  RequestMethod.GET , value = "/detectMediaEmotion")
   // @ResponseBody
    public String detectMediaEmotion (@RequestParam(value = "id")String id){
        return mediaServices.detectMediaEmotion(id);
        //"1077130043483313296_2111218668" örnek id ve "url" : "https://scontent.cdninstagram.com/t51.2885-15/s320x320/e35/11363830_1631745140433924_1838665717_n.jpg";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/detected")
    public String checkMediaEmotion(@ModelAttribute FaceAPIModel model,Media media){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String attributes = model.getAttributes() != null ? model.getAttributes() : faceAttributes;

        headers.set("Ocp-Apim-Subscription-Key", "5fa5004578ea4d79b6d9c921ad8b2d9c");

        String body = "{ \"url\": \"" + " https://scontent.cdninstagram.com/t51.2885-15/s320x320/e35/11363830_1631745140433924_1838665717_n.jpg " + "\" }";

        HttpEntity<String> entity = new HttpEntity<String >(body, headers);

        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host("westus.api.cognitive.microsoft.com").path("/emotion/v1.0/recognize")
                .buildAndExpand(attributes);

        ResponseEntity<String> result = restTemplate.postForEntity(uriComponents.toString(),entity,String.class);




        return result.getBody();
    }





}
