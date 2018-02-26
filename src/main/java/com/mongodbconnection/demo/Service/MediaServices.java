package com.mongodbconnection.demo.Service;

import com.mongodbconnection.demo.Config.FaceAPIEnv;
import com.mongodbconnection.demo.Model.Media;
import com.mongodbconnection.demo.Repository.MediaRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class MediaServices {
    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    FaceAPIEnv faceAPIEnv;

    @Value("${face.attributes}")
    private String faceAttributes;

    @SuppressWarnings("Duplicates")
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

        /*
        //Emotion api key- value ilişkisi ile tanıyor
        headers.set("Content-Type", "application/json");
        headers.set("Ocp-Apim-Subscription-Key", "5fa5004578ea4d79b6d9c921ad8b2d9c");
        */

        //Face api key- value ilişkisi ile tanıyor
        headers.set("Content-Type", "application/json");
        headers.set("Ocp-Apim-Subscription-Key", "e5140c883f7f4c02ac24b61b2fac0a0d");

        //Media'dan kişinin fotoğraf url ulaşıyor
        String body = "{ \"url\": \"" +  media.getImages().getLow_resolution().getUrl() + "\" }";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        /*
        //Emotion api'nin kullanmış olduğu URL'e post atmak için tanımlıyoruz
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host("westus.api.cognitive.microsoft.com").path("/emotion/v1.0/recognize")
                .buildAndExpand(faceAttributes);
        */

        //Facce api'nin kullanmış olduğu URL'e post atmak için tanımlıyoruz
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host("westcentralus.api.cognitive.microsoft.com").path("/face/v1.0/detect")
                .queryParam("returnFaceId", "true")
                .queryParam("returnFaceLandmarks", "false")
                .queryParam("returnFaceAttributes", "age,gender,smile,emotion").buildAndExpand();
        //"https://westus.api.cognitive.microsoft.com/face/v1.0/detect"
        //"https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect"


        ResponseEntity<String> result = restTemplate.postForEntity(uriComponents.toString(),entity,String.class);

        //Converting json response to string
        if (result !=null){
            try {
              // System.out.println(media.toString());
                JSONArray jsonArray = new JSONArray(result.getBody());
                System.out.println("json array  : "+jsonArray.toString());
                for (int i = 0; i<jsonArray.length(); i++){
                    JSONObject jsonObject =jsonArray.getJSONObject(i);
                    if (!jsonObject.isNull("faceAttributes")){
                        JSONObject scores = jsonObject.getJSONObject("faceAttributes");
                        if (!scores.isNull("smile")){
                            System.out.println("Line 84   bir alt satırdaki yeni değeridir.  "+scores.getString("smile")+"      images: "+media.getImages().getLow_resolution().getUrl());
                            return scores.getString("smile");
                        }
                        else if (!scores.isNull("emotion")){
                            JSONObject  happines = scores.getJSONObject("emotion");
                            if (!happines.isNull("happiness")){
                                System.out.println("happines values is :    "+  happines.getString("happiness"));
                            }

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
                    if (jsonObject.isNull("faceAttributes")){
                        JSONObject scores = jsonObject.getJSONObject("faceAttributes");
                        if (scores.isNull("smile")){
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
/*
    public String detectMediaEmotionWithFaceApi(String id ){
        Media media =  mediaRepository.findById(id);

        //Veriyi convert işlemine sokuyor
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        //Alınacak emotion api gelen veri json formatında olduğu için
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //Face api key- value ilişkisi ile tanıyor
        headers.set("Content-Type", "application/json");
        headers.set("Ocp-Apim-Subscription-Key", "c163c2a7e5d443ee891c193903ab36f6");

        //Media'dan kişinin fotoğraf url ulaşıyor
        String body = "{ \"url\": \"" +  media.getImages().getLow_resolution().getUrl() + "\" }";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        //Facce api'nin kullanmış olduğu URL'e post atmak için tanımlıyoruz
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host("westcentralus.api.cognitive.microsoft.com").path("/face/v1.0/detect")
                .queryParam("returnFaceId", "true")
                .queryParam("returnFaceLandmarks", "false")
                .queryParam("returnFaceAttributes", "age,gender,smile").buildAndExpand();
        //"https://westus.api.cognitive.microsoft.com/face/v1.0/detect"
        //"https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect"

        ResponseEntity<FaceValues>result = restTemplate.exchange(uriComponents.toString(), HttpMethod.POST,entity,FaceValues.class);

        FaceValues faceValues = result.getBody();

        FaceAttributes faceAttributes= faceValues.getFaceAttributes();



    return "asd";
    }
*/

    public String findHappiestMoment(String mediaOwner){
        //Getting all medialist by OwnerId
        List<Media> mediaList = mediaRepository.findByMediaOwner(mediaOwner);
        //Controlling media is exist
        if (mediaList!=null) {
            System.out.println("Medialist size :    "+mediaList.size());
            double maxHappinesValue = Integer.MIN_VALUE;
            int tempId = 0;
            for (int i = 0; i < mediaList.size(); i++) {
                double tempMediaEmotion = Double.parseDouble(detectMediaEmotion(mediaList.get(i).getId()));
                System.out.println("line    123         maxHappinesValue    is  :      " + maxHappinesValue + "           " + (i + 1) + "   images :    " + mediaList.get(i).getImages().getLow_resolution().getUrl());
                if (tempMediaEmotion > maxHappinesValue) {
                    tempId = i;
                    maxHappinesValue = tempMediaEmotion;
                } else if ((i + 1) % 20 == 0) {
                    try {
                        TimeUnit.SECONDS.sleep(65);
                        System.out.println("Line 129 if statment");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                //No more media file and scores.happines is equal to maxHappinesValue
                System.out.println("Last maxHappinesValues is   :       " + maxHappinesValue + "       Id:    " + (tempId + 1) + "   picture is  :   " + mediaList.get(tempId).getImages().getLow_resolution().getUrl());
                // return String.valueOf(maxHappinesValue);
            }
        }
        else {
            //Media is not exist than happines value is '0'
            return String.valueOf(0);
        }
        return String.valueOf(0);
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


   // @Scheduled(fixedRate = 40000 )
    public String findHappiestMoment2(String mediaOwner){
        try {
            TimeUnit.SECONDS.sleep(65);
            List<Media> mediaList = mediaRepository.findByMediaOwner(mediaOwner);
            if (mediaList!=null){
                double maxHappinesValue = Integer.MIN_VALUE;
                int tempId=0;
                if (mediaList.size()>19){
                    for (int i =19; i<mediaList.size();i++){
                        if (i<=40){
                            System.out.println("line 168            "+  i);
                            double tempMediaEmotion = Double.parseDouble(detectMediaEmotion(mediaList.get(i).getId()));
                            if (tempMediaEmotion   >   maxHappinesValue){
                                tempId=i;
                                maxHappinesValue= tempMediaEmotion;
                                System.out.println("Line 155     maxHappinesValues is :       "+maxHappinesValue+"          "+i);
                            }
                        }
                        else {
                            break;
                        }
                    }
                }
                System.out.println("Last **maxHappinesValues** is   :       "+maxHappinesValue+ "       Id:    "+(tempId+1));
                return String.valueOf(maxHappinesValue);
            }
            else {
                return String.valueOf(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(0);

    }



*/
