package com.example.javacapstone.repositories;

import com.example.javacapstone.model.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {

    Optional<PublisherEntity> findById(Long id);


    Optional<PublisherEntity> findByName(String name);
}
