package com.example.javacapstone.controllers;

import com.example.javacapstone.model.PublisherEntity;
import com.example.javacapstone.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Flow;

@Controller
@RestController

@RequestMapping("/publishers")
public class PublisherController {
    @Autowired
    public PublisherRepository publisherRepository;


    @PutMapping("/{id}")
    public ResponseEntity<?> updatePublisher(@PathVariable Long id, @RequestBody PublisherEntity publisherEntity) {
        Optional<PublisherEntity> optionalPublisher = publisherRepository.findById(id);
        if (optionalPublisher.isPresent()) {
            PublisherEntity existingPublisher = optionalPublisher.get();
            existingPublisher.setName(publisherEntity.getName());
            publisherRepository.save(existingPublisher);
            return ResponseEntity.ok(existingPublisher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping("/")
    public String getIndexPage(){ return "index"; }

    @RequestMapping("/AddPublisherForm")
    public String addGameForm(){ return "addPublisher";}
}
