package com.mongodbconnection.demo.Service;

import com.mongodbconnection.demo.Config.FaceAPIEnv;
import com.mongodbconnection.demo.Model.FaceAPIModel;
import com.mongodbconnection.demo.Model.Media;
import com.mongodbconnection.demo.Repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import sun.rmi.runtime.Log;


@Service
public class MediaServices {
    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    FaceAPIEnv faceAPIEnv;

    @Value("${face.attributes}")
    private String faceAttributes;

    public String detectMediaEmotion(String id ){
        Media media =  mediaRepository.findById(id);

        //Veriyi convert işlemine sokuyor
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        //Alınacak emotion api gelen veri json formatında olduğu için
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //Emotion api için özellikleri alıyor şuan için face.attributes=age,emotion application.properties'den alıyor
        //Bunu kaldırmamızın sebebi ModelAttribute service içerisnden çalışmıyor
        //Çalışması için RestController gerekiyor
        //String attributes = model.getAttributes() != null ? model.getAttributes() : faceAttributes;

        //Emotion api key- value ilişkisi ile tanıyor
        headers.set("Content-Type", "application/json");
        headers.set("Ocp-Apim-Subscription-Key", "5fa5004578ea4d79b6d9c921ad8b2d9c");

        //Media'dan kişinin fotoğraf url ulaşıyor
        String body = "{ \"url\": \"" +  media.getImages().getLow_resolution().getUrl() + "\" }";

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);

        //Emotion api'nin kullanmış olduğu URL'e post atmak için tanımlıyoruz
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host("westus.api.cognitive.microsoft.com").path("/emotion/v1.0/recognize")
                .buildAndExpand(faceAttributes);
        System.out.println(faceAttributes);

        ResponseEntity<String> result = restTemplate.postForEntity(uriComponents.toString(),entity,String.class);

        if (result !=null){
            System.out.println("İf statment line 65");
            System.out.println( media.toString());
            return result.getBody();

        }
        else {
            System.out.println("Else statment line 70");
            return "Can not detecting Face";
        }
    }
}

         /*
          RequestMapping(method = RequestMethod.POST, value = "/detect")
    public String checkMediaEmotion(@ModelAttribute FaceAPIModel model,Media media){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String attributes = model.getAttributes() != null ? model.getAttributes() : faceAttributes;

        headers.set("Ocp-Apim-Subscription-Key", "5fa5004578ea4d79b6d9c921ad8b2d9c");

        String body = "{ \"url\": \"" +  media.getUsers_in_photo().size() + "\" }";

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);

        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host("westus.api.cognitive.microsoft.com").path("/emotion/v1.0/recognize")
                .buildAndExpand(attributes);

        ResponseEntity<String> result = restTemplate.postForEntity(uriComponents.toString(),entity,String.class);


        return result.getBody();
    }


    public String detectMediaEmotion(@ModelAttribute FaceAPIModel model, String id ){
        Media media =  mediaRepository.findById(id);
        System.out.println(media.getId()+"      "+media.getUsers_in_photo().size()+"        "+media.getImages().getLow_resolution().getUrl());

        //Veriyi convert işlemine sokuyor
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        //Alınacak emotion api gelen veri json formatında olduğu için
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //Emotion api için özellikleri alıyor şuan için face.attributes=age,emotion application.properties'den alıyor
        String attributes = model.getAttributes() != null ? model.getAttributes() : faceAttributes;

        //Emotion api key- value ilişkisi ile tanıyor
        headers.set("Ocp-Apim-Subscription-Key", "5fa5004578ea4d79b6d9c921ad8b2d9c");

        //Media'dan kişinin fotoğraf url ulaşıyor
        String body = "{ \"url\": \"" +  media.getImages().getLow_resolution().getUrl() + "\" }";

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);

        //Emotion api'nin kullanmış olduğu URL'e post atmak için tanımlıyoruz
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host("westus.api.cognitive.microsoft.com").path("/emotion/v1.0/recognize")
                .buildAndExpand(attributes);

        ResponseEntity<String> result = restTemplate.postForEntity(uriComponents.toString(),entity,String.class);


        return result.getBody();


    }

*/
