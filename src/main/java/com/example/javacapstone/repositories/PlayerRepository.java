package com.example.javacapstone.repositories;

import com.example.javacapstone.model.PlayerEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    void save(PlayerEntity existingPlayer);

    Optional<PlayerEntity> findById(Long id);
}
