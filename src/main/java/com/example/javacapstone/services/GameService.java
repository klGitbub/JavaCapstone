package com.example.javacapstone.services;

import com.example.javacapstone.model.GameEntity;
import com.example.javacapstone.model.PublisherEntity;
import com.example.javacapstone.repositories.GameRepository;
import com.example.javacapstone.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public void createGame(GameEntity gameEntity){

        PublisherEntity publisher = publisherRepository.findById(gameEntity.getPublisher_id()).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Publisher not found!");
        });

        GameEntity newGame = new GameEntity();
        newGame.setGame_name(gameEntity.getGame_name());
        newGame.setGame_platform(gameEntity.getGame_platform());
        newGame.setGame_developer(gameEntity.getGame_developer());
        newGame.setPublisherEntity(publisher);
        newGame.setGenre(gameEntity.getGenre());

        gameRepository.save(newGame);
    }

    public List<GameEntity> getGames(){
        return gameRepository.findAll();
    }
    public ResponseEntity<?> updateGame(Long id, GameEntity gameEntity){
        Optional<GameEntity> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()) {
            GameEntity existingGame = optionalGame.get();
            existingGame.setGame_name(gameEntity.getGame_name());
            existingGame.setGame_developer(gameEntity.getGame_developer());
            existingGame.setGenre(gameEntity.getGenre());
            existingGame.setPublisherEntity(gameEntity.getPublisherEntity());
            gameRepository.save(existingGame);
            return ResponseEntity.ok(existingGame);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
