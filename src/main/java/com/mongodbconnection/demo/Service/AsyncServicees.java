package com.mongodbconnection.demo.Service;

import com.mongodbconnection.demo.Config.MyErrorHandler;
import com.mongodbconnection.demo.Model.*;
import com.mongodbconnection.demo.Repository.CognitiveServiceRepository;
import com.mongodbconnection.demo.Repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
//@Component
@Service
//@EnableAsync
public class AsyncServicees {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CognitiveServiceRepository cognitiveServiceRepository;

    @Autowired
    MediaServices mediaServices;

    @Value("${face.attributes}")
    public String faceAttributes;

    //@Async("asyncExecutor")

    //@Transactional
    @Async
    public void getUserAccesTokenFromId(String userId, String userRequestId, String url, List<Data> list, CognitiveServiceMediaStatus cognitiveServiceMediaStatus) {
        System.out.println("DENEME");
        MaxHappinesValues maxHappinesValues = new MaxHappinesValues();

        //Kullanıcının kendi bilgileri
        User ownUser = userRepository.findOne(userId);
        //İstediği kullanıcının bilgileri
       // User requestUser = userRepository.findOne(userRequestId);

        //Veriyi convert işlemine sokuyor
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        //Alınacak emotion api gelen veri json formatında olduğu için
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        //Resmini istediği kullanıcının id 'si

       // String user_id = requestUser.getId();

        //Programı kullanan kullanıcınn kendi accesToken'ı
        String accesToken = ownUser.getAccess_token();
        //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKE           =default URL
        //https://api.instagram.com/v1/users/6895752190/media/recent/?access_token=1949294863.615654b.041e8da34c8f4bc482d3369bb92ffc34
        //https://api.instagram.com/v1/users/1083659435/media/recent/?access_token=1083659435.615654b.778eafddc4594ab59a949f9cefd0c2ba
        //https://api.instagram.com/v1/users/288633852/media/recent/?access_token=1949294863.615654b.041e8da34c8f4bc482d3369bb92ffc34
        if (url == null) {
            UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                    .host("api.instagram.com")
                    .path("/v1/users/")
                    .path(userRequestId)
                    .path("/media/recent/")
                    .path("?access_token=")
                    .path(accesToken)
                    .build();
            url = uriComponents.toString();
        }

        if (list == null)
            list = new ArrayList<>();

//        if (cognitiveServiceMediaStatus ==null){
//        //    private String id;
//        //    private String status;
//        //    private String maxHappinesValueUrl;
//        //    private String maxHappinesValue;
//            cognitiveServiceMediaStatus = new CognitiveServiceMediaStatus();
//            String totalId = userId+ userRequestId;
//            cognitiveServiceMediaStatus.setId(totalId);
//            cognitiveServiceMediaStatus.setStatus("Updating");
//            cognitiveServiceRepository.save(cognitiveServiceMediaStatus);
//        }
        //https://api.instagram.com/v1/users/1083659435/media/recent/?access_token=1083659435.615654b.778eafddc4594ab59a949f9cefd0c2ba
        // https://api.instagram.com/v1/users/1083659435/media/recent?access_token=1083659435.615654b.778eafddc4594ab59a949f9cefd0c2ba---------&max_id=1542093674241016697_1083659435

        //Handle status code : 400 type : Bad Request error handler
        restTemplate.setErrorHandler(new MyErrorHandler());
        //Response instagram api
        ResponseEntity<RequestMedia> result = restTemplate.exchange(url, HttpMethod.GET, entity, RequestMedia.class);

        //ResponseEntity<RequestMedia>  result = restTemplate.exchange(uriComponents.toString(),HttpMethod.GET,entity,RequestMedia.class);

        RequestMedia requestMedia = result.getBody();

        Pagination pagination = requestMedia.getPagination();

        System.out.println(requestMedia.toString());

        //200 'den başka gelecek herhangi bir hata kodu için hata üretiyoruz
        //Kullanıcı profili kapalı bir kullanıcının resmini isterse o zaman getiremiyecek ve hata dondurmesı gerekecek
        if (requestMedia.getMeta().getCode() != 200) {
            if(requestMedia.getMeta().getError_message().equals("you cannot view this resource")){
                cognitiveServiceMediaStatus = new CognitiveServiceMediaStatus();
                cognitiveServiceMediaStatus.setId(userId+userRequestId);
                cognitiveServiceMediaStatus.setStatus("you cannot view this resource");
                cognitiveServiceRepository.save(cognitiveServiceMediaStatus);
                //textWatcher
                //
            }
            System.out.println("Line 66   :   " + requestMedia.getMeta().getCode() + "  " + requestMedia.getMeta().getError_type() + "  " + requestMedia.getMeta().getError_message());
            //return maxHappinesValues;
        } else {
            MaxHappinesValues tempMaxHappinesValues = new MaxHappinesValues();
            //Bütün dataları liste yükledikten sonra cognitive services göndereceğiz
            list.addAll(requestMedia.getData());

            //NextUrl kontrol ediyoruz ve listenin size'ı 100 den kucukse verileri işlecek
            System.out.println("Line 99 " + pagination.getNext_url());

            if (pagination.getNext_url() != null && list.size() < 100) {
                if (pagination.getNext_url() != null) {
                    System.out.println("/********************************************************************************************/");
                    String nextUrl = pagination.getNext_url();
                    //maxHappinesValues=getUserAccesTokenFromId(userId, userRequestId, nextUrl, list,cognitiveServiceMediaStatus);
                    getUserAccesTokenFromId(userId, userRequestId, nextUrl, list, cognitiveServiceMediaStatus);
//                    maxHappinesValues.setCognitiveServiceMediaStatus(cognitiveServiceMediaStatus.getStatus());
                    //return maxHappinesValues;
                } else {
                    System.out.println("/-----------------------------  NextURL not exist -----------------------------------------------------------------/");
                    // return maxHappinesValues;
                }
            } else {
                System.out.println("************************    Next URL not exist anymore ***************************");
                maxHappinesValues = findHappiestMoment(list);
                //findHappiestMoment(list);
                String totalId = userId + userRequestId;
                CognitiveServiceMediaStatus mediaStatus = cognitiveServiceRepository.findOne(totalId);
                mediaStatus.setId(totalId);
                mediaStatus.setStatus("Done");
                mediaStatus.setMaxHappinesValueUrl(maxHappinesValues.getMaxHappinesValueUrl());
                mediaStatus.setMaxHappinesValue(String.valueOf(maxHappinesValues.getHappinesValue()));
                cognitiveServiceRepository.save(mediaStatus);
                //asenkron olacak ve aynı sınıf içerisinde olmuyor ayrıca asyn diye service açacaksın service orada autowired edeceksin daha sonra
                //o clasın içerisinde istekleri yapacaksın sadece
                //application oraya async yazsak yeterli

//                maxHappinesValues.setCognitiveServiceMediaStatus(mediaStatus.getStatus());
                //tam 20 'de 65 saniye bekliyor onun onune geçmek için 65 sanıye bekletme   *! önemli

                //return maxHappinesValues;
            }
        }
        // return "Code    :   " + requestMedia.getMeta().getCode();
        //   return maxHappinesValues;
    }

    //@Async
    public MaxHappinesValues findHappiestMoment(List<Data> data) {
        MaxHappinesValues maxHappinesValues = new MaxHappinesValues();
        double maxHappinesValue = Integer.MIN_VALUE;
        if (data != null) {
            int tempId = 0;

            for (int i = 0; i < data.size(); i++) {
                /*--------------------------------------------------------------*/
                double tempMediaEmotion = Double.parseDouble(detectMediaEmotionWithFaceApi(data.get(i).getImages().getLow_resolution().getUrl()));
                /*------------------------------------------------------------------*/
                //double tempMediaEmotion = Double.parseDouble(detectMediaEmotion(data.get(i).getImages().getLow_resolution().getUrl()));
                /*--------------------------------------------------------------*/
                System.out.println("tempMediaEmotion values is :     " + tempMediaEmotion + "  id   :    " + (i + 1) + " images  :   " + data.get(i).getImages().getLow_resolution().getUrl());
                if (tempMediaEmotion > maxHappinesValue) {
                    tempId = i;
                    maxHappinesValue = tempMediaEmotion;
                } else if ((i + 1) % 20 == 0) {
                    try {
                        TimeUnit.SECONDS.sleep(65);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Last maxHappines value is : " + maxHappinesValue + "   Id  :   " + (tempId + 1) +
                    "    pictures    :   " +
                    data
                            .get(tempId)
                            .getImages()
                            .getLow_resolution()
                            .getUrl() + " Status is " + maxHappinesValues.getCognitiveServiceMediaStatus());
            maxHappinesValues.setHappinesValue(maxHappinesValue);
            maxHappinesValues.setMaxHappinesValueId((tempId + 1));
            maxHappinesValues.setMaxHappinesValueUrl(data.get(tempId).getImages().getLow_resolution().getUrl());

            return maxHappinesValues;
        } else {
            return maxHappinesValues;
        }
        //return maxHappinesValues;

    }

    @SuppressWarnings("Duplicates")
    //@Async
    public String detectMediaEmotionWithFaceApi(String bodyComeByFindHappiestMoment) {
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
        String body = "{ \"url\": \"" + bodyComeByFindHappiestMoment + "\" }";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        //Face api'nin kullanmış olduğu URL'e post atmak için tanımlıyoruz
        //Facce api'nin kullanmış olduğu URL'e post atmak için tanımlıyoruz
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host("westcentralus.api.cognitive.microsoft.com").path("/face/v1.0/detect")
                .queryParam("returnFaceId", "true")
                .queryParam("returnFaceLandmarks", "false")
                .queryParam("returnFaceAttributes", "age,gender,smile").buildAndExpand();
        //"https://westus.api.cognitive.microsoft.com/face/v1.0/detect"
        //"https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect"

        ResponseEntity<String> result = restTemplate.postForEntity(uriComponents.toString(), entity, String.class);


        if (result.hasBody() && result.getBody() != null) {
            try {
                JSONArray jsonArray = new JSONArray(result.getBody());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (!jsonObject.isNull("faceAttributes")) {
                        JSONObject faceAttributesJsonObject = jsonObject.getJSONObject("faceAttributes");
                        if (!faceAttributesJsonObject.isNull("smile")) {
                            System.out.println("smile    :  " + faceAttributesJsonObject.getString("smile"));
                            return faceAttributesJsonObject.getString("smile");
                        } else if (!faceAttributesJsonObject.isNull("emotion")) {
                            JSONObject happines = faceAttributesJsonObject.getJSONObject("emotion");
                            if (!happines.isNull("happiness")) {

                                System.out.println("happines values is :    " + happines.getString("happiness"));
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return String.valueOf(0);
        } else {
            //Controlling if media still exist
            try {
                JSONArray jsonArray = new JSONArray(result.getBody());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.isNull("faceAttributes")) {
                        JSONObject scores = jsonObject.getJSONObject("faceAttributes");
                        if (scores.isNull("smile")) {
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
}
