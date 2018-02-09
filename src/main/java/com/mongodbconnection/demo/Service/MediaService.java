package com.mongodbconnection.demo.Service;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodbconnection.demo.Entity.Media;
import com.mongodbconnection.demo.Repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.MongoCollectionUtils;
import org.springframework.data.mongodb.core.MongoDbUtils;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Service
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

  /*  @RequestMapping(method = RequestMethod.GET, value = "findAllMedia")
    public List<Media> findAllMediaFiles(){
       String mediaId="1688264887456000716_1386731221";
        List<Media> result = repository.findByMediaId(mediaId);
        System.out.println(result..getUsers_in_photo().size());
        return result;
    }*/

    @RequestMapping(method = RequestMethod.GET, value = "findAllMedia")
    public String findAllMediaFiles(){
        String mediaId="1688264887456000716_1386731221";
       /* for (Media media : repository.findByMediaId(mediaId.toString())){
            System.out.println(media.getUsers_in_photo().size());
            System.out.println(media.getImages());
            System.out.println(media.getMediaId());
        }*/


       repository.findByMediaId(mediaId);
        Media media = repository.findOne(mediaId);
        return media.getMediaId()+"----------"+media.getUsers_in_photo().size();

    }
}
