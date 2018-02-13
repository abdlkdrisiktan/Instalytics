package com.mongodbconnection.demo.Service;

import com.mongodbconnection.demo.Config.FaceAPIEnv;
import com.mongodbconnection.demo.Model.Media;
import com.mongodbconnection.demo.Repository.MediaRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


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

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        //Emotion api'nin kullanmış olduğu URL'e post atmak için tanımlıyoruz
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host("westus.api.cognitive.microsoft.com").path("/emotion/v1.0/recognize")
                .buildAndExpand(faceAttributes);

        ResponseEntity<String> result = restTemplate.postForEntity(uriComponents.toString(),entity,String.class);

        //Converting json response to string
        if (result !=null){
            try {
                //--------------------------------------------------Burayı ilerde açacaksın System.out.println(media.toString());
                JSONArray jsonArray = new JSONArray(result.getBody());
                for (int i = 0; i<jsonArray.length(); i++){
                    JSONObject jsonObject =jsonArray.getJSONObject(i);
                    if (!jsonObject.isNull("scores")){
                        JSONObject scores = jsonObject.getJSONObject("scores");
                        if (!scores.isNull("happiness")){
                            System.out.println("Line 84     "+scores.getString("happiness"));
                            return scores.getString("happiness");
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return String.valueOf(0);
        }
        else {
            //Controlling if media still exist
            try {
                JSONArray jsonArray = new JSONArray(result.getBody());
                for (int i =0; i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.isNull("scores")){
                        JSONObject scores = jsonObject.getJSONObject("scores");
                        if (scores.isNull("happiness")){
                            System.out.println("Line    102     ");
                            //İf media still exist then scores.happiness make '0'
                            return String.valueOf(0);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println("Else statment line 70");
            return String.valueOf(0);
        }
    }

    public String findHappiestMoment(String mediaOwner){
        //Getting all medialist by OwnerId
        List<Media> mediaList = mediaRepository.findByMediaOwner(mediaOwner);
        //Controlling media is exist
        if (mediaList!=null){
            double maxHappinesValue = Integer.MIN_VALUE;
            for (int i= 0; i<mediaList.size(); i++){
               /* System.out.println(mediaList.get(i).getMediaOwner()+"   "+mediaList.get(i).getId()+"    "
                        +mediaList.get(i).getUsers_in_photo().size()+"     "+mediaList.get(i).getImages().getLow_resolution().getUrl());*/
                 double tempMediaEmotion = Double.parseDouble(detectMediaEmotion(mediaList.get(i).getId()));
                 if (tempMediaEmotion   >   maxHappinesValue){
                    maxHappinesValue= tempMediaEmotion;
                    System.out.println("Line 114     maxHappinesValues is :       "+maxHappinesValue+"          "+i);
                }
            }
            //No more media file and scores.happines is equal to maxHappinesValue
            return String.valueOf(maxHappinesValue);
        }
        else {
            //Media is not exist than happines value is '0'
            return String.valueOf(0);
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
