package com.mongodbconnection.demo.Service;

import com.mongodbconnection.demo.Entity.Media;
import com.mongodbconnection.demo.Repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaService {


    @Autowired
    private MediaRepository repository;

    /*public List<Media> getMedia(){
        List list = repository.findAll();
        for (int i=0;i<10;i++){
            System.out.println(list.get(i));
        }

        return list;

    }*/
    @RequestMapping(method = RequestMethod.GET,value = "media")
    public Page<Media> getMedia(){
        Page<Media> mediaPage = repository.findAll(new PageRequest(0,25));
        return mediaPage;
    }
}
