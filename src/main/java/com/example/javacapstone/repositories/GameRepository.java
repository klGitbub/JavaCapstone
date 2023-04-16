package com.example.javacapstone.repositories;

import com.example.javacapstone.model.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {
    Optional<GameEntity> findById(Long id);

}
