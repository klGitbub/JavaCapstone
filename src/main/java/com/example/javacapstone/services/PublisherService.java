package com.example.javacapstone.services;

import com.example.javacapstone.model.PublisherEntity;
import com.example.javacapstone.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;


    public List<PublisherEntity> getPublishers(){
        return publisherRepository.findAll();
    }
    public void createPublisher(PublisherEntity publisherEntity){
        Boolean publisherExists =  publisherRepository.findByName(publisherEntity.getName()).isPresent();
        if(publisherExists){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Publisher already exist!");
        }

        PublisherEntity newPublisher = new PublisherEntity();
        newPublisher.setName(publisherEntity.getName());

        publisherRepository.save(newPublisher);
    }

    public ResponseEntity<?> updatePublish(Long id, PublisherEntity publisherEntity){
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
}
