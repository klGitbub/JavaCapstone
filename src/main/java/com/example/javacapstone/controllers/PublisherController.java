package com.example.javacapstone.controllers;

import com.example.javacapstone.model.PublisherEntity;
import com.example.javacapstone.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController

@RequestMapping("/publishers")
public class PublisherController {
    @Autowired
    public PublisherService publisherService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPublisher(@RequestBody PublisherEntity publisherEntity) {
        publisherService.createPublisher(publisherEntity);
    };

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PublisherEntity> getPublishers(){
        return publisherService.getPublishers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePublisher(@PathVariable Long id, @RequestBody PublisherEntity publisherEntity) {
        return publisherService.updatePublish(id, publisherEntity);
    }
}
