package com.mongodbconnection.demo.Service;

import com.mongodbconnection.demo.Entity.Media;
import com.mongodbconnection.demo.Repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MediaService {


    @Autowired
    private MediaRepository repository;
/*
    @RequestMapping(method = RequestMethod.GET,value = "media")
    public List<Media> getMedia(){
        List list = repository.findAll();
        for (int i=0;i<10;i++){
            System.out.println(list.get(i));
        }

        return list;

    }*/

    @RequestMapping(method = RequestMethod.GET,value = "media")
    public Page<Media> getMedia(){
        Page<Media> mediaPage = repository.findAll(new PageRequest(0,25));

        for (Media media : repository.findAll(new PageRequest(0,25))){
            System.out.println(media.getUsers_in_photo());
            System.out.println(media.getImages().getLow_resolution().getUrl());

        }
        return mediaPage;
    }
}
