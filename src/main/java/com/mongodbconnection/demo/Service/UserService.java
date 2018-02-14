package com.mongodbconnection.demo.Service;


import com.mongodbconnection.demo.Config.MyErrorHandler;
import com.mongodbconnection.demo.Model.RequestMedia;
import com.mongodbconnection.demo.Model.User;
import com.mongodbconnection.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MediaServices mediaServices;

    public String getUserAccesTokenFromId(String userId,String userRequestId){

        //Kullanıcının kendi bilgileri
        User ownUser = userRepository.findOne(userId);
        //İstediği kullanıcının bilgileri
        User requestUser = userRepository.findOne(userRequestId);

        //Veriyi convert işlemine sokuyor
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        //Alınacak emotion api gelen veri json formatında olduğu için
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        //Resmini istediği kullanıcının id 'si
        String user_id= requestUser.getId();
        //Programı kullanan kullanıcınn kendi accesToken'ı
        String accesToken= ownUser.getAccess_token();
        //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKE           =default URL
        //https://api.instagram.com/v1/users/1083659435/media/recent/?access_token=1083659435.615654b.778eafddc4594ab59a949f9cefd0c2ba
        //https://api.instagram.com/v1/users/288633852/media/recent/?access_token=1949294863.615654b.041e8da34c8f4bc482d3369bb92ffc34
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host("api.instagram.com")
                .path("/v1/users/")
                .path(user_id)
                .path("/media/recent/")
                .path("?access_token=")
                .path(accesToken)
                .build();
        System.out.println(uriComponents.toString());


        restTemplate.setErrorHandler(new MyErrorHandler());
        ResponseEntity<RequestMedia>  result = restTemplate.exchange(uriComponents.toString(),HttpMethod.GET,entity,RequestMedia.class);
        RequestMedia requestMedia = result.getBody();
            if (requestMedia.getMeta().getCode()!=200){

                System.out.println("Line 66   :   "+requestMedia.getMeta().getCode()+"  "+requestMedia.getMeta().getError_type()+"  "+requestMedia.getMeta().getError_message());
                return  "Error message  :   "+requestMedia.getMeta().getError_message()+"Error message Type   :   "+requestMedia.getMeta().getError_type();
                //return result.stat(HttpStatus.BAD_REQUEST).toString();
             }
             else {

                System.out.println("JSON string "+result.getBody().toString());
                System.out.println("Line 56 " + RequestMedia.class.toString() );
                return "Code    :   "+requestMedia.getMeta().getCode();

            }
        //return "working";
    }
    /*
    public String getMediaByOtherUser(String mediaOwner,List<Media> mediaList){
        //Veriyi convert işlemine sokuyor
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        String user_id="1083659435";
        String accesToken="1083659435.615654b.778eafddc4594ab59a949f9cefd0c2ba";
        //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKE
        //https://api.instagram.com/v1/users/1083659435/media/recent/?access_token=1083659435.615654b.778eafddc4594ab59a949f9cefd0c2ba
        //https://api.instagram.com/v1/users/123123123/media/recent/1321231231?acces_token=
        //https://api.instagram.com/v1/users/123123123/media/recent/?access_token=abcds
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host("api.instagram.com")
                .path("/v1/users/")
                .path(mediaOwner)
                .path("/media/recent/")
                .path("?access_token=")
                .path(accesToken)
                .build();

        System.out.println(uriComponents.toString());

       // mediaList =mediaRepository.findByMediaOwner(mediaOwner);




        ResponseEntity<String> result = restTemplate.getForEntity(uriComponents.toString(),String.class);



    }*/

}
