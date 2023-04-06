package com.example.javacapstone.repositories;

import com.example.javacapstone.model.GameEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {
    Optional<GameEntity> findById(Long id);

    void save(GameEntity existingGame);
}
