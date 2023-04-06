package com.example.javacapstone.repositories;

import com.example.javacapstone.model.PublisherEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository {
    Optional<PublisherEntity> findById(Long id);

    void save(PublisherEntity existingPublisher);
}
