package com.mongodbconnection.demo.Controller;

import com.mongodbconnection.demo.Config.FaceAPIEnv;
import com.mongodbconnection.demo.Model.FaceAPIModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
public class FaceAPIController {
   /* @Autowired
    private FaceAPIEnv faceAPIEnv;

    @Value("${sample.url}")
    private String sampleUrl;
    @Value("${face.attributes}")
    private String faceAttributes;


    @RequestMapping("/detect")
    public String check(@ModelAttribute FaceAPIModel model){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Form values take precedence
        String key = model.getKey() != null ? model.getKey() : faceAPIEnv.getKey();
        String url = model.getUrl() != null ? model.getUrl() : sampleUrl;
        String attributes = model.getAttributes() != null ? model.getAttributes() : faceAttributes;

        headers.set("Ocp-Apim-Subscription-Key", key);

        String body = "{ \"url\": \"" + url + "\" }";

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);

        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host("westcentralus.api.cognitive.microsoft.com").path("/face/v1.0/detect")
                .query("returnFaceAttributes={keyword}").buildAndExpand(attributes);

        ResponseEntity<String> result = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.POST, entity,
                String.class);

        return result.getBody();
    }*/
   @Autowired
   private FaceAPIEnv faceAPIEnv;

    @Value("${sample.url}")
    private String sampleUrl;
    @Value("${face.attributes}")
    private String faceAttributes;

    @RequestMapping(method = RequestMethod.POST, value = "/detect")
    public String check (@ModelAttribute FaceAPIModel model){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        String key = model.getKey() != null ? model.getKey() : faceAPIEnv.getKey();
        String url = model.getUrl() != null ? model.getUrl() : sampleUrl;
        String attributes = model.getAttributes() != null ? model.getAttributes() : faceAttributes;

        headers.set("Ocp-Apim-Subscription-Key", "5fa5004578ea4d79b6d9c921ad8b2d9c");
       // headers.set("Ocp-Apim-Subscription-Key",key);

        String body = "{ \"url\": \"" + "https://scontent.cdninstagram.com/t51.2885-19/s150x150/15035533_1087415941383795_8278921514582016000_a.jpg" + "\" }";

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);

        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
                .host("westus.api.cognitive.microsoft.com").path("/emotion/v1.0/recognize")
                .buildAndExpand(attributes);

        System.out.println(uriComponents.toString());

        //https://westus.api.cognitive.microsoft.com/emotion/v1.0/recognize

        System.out.println(key);
        System.out.println(url);
       // ResponseEntity<String> result = restTemplate.getForEntity("https://stackoverflow.com/questions/38019212/org-springframework-web-client-httpclienterrorexception-400-bad-request",String.class);


       // ResponseEntity<String> result = restTemplate.exchange(uriComponents.toString(),HttpMethod.POST,entity,String.class);


        ResponseEntity<String> result = restTemplate.postForEntity(uriComponents.toString(),entity,String.class);

        // ResponseEntity<String> result = restTemplate.getForEntity("https://westus.api.cognitive.microsoft.com/emotion/v1.0",String.class);
      //  ResponseEntity<String> result = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.POST, entity,
       //         String.class);

       // return result.getBody();
        return result.getBody();

    }




}
