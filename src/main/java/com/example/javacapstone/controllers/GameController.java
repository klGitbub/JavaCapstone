package com.example.javacapstone.controllers;

import com.example.javacapstone.repositories.GameRepository;
import com.example.javacapstone.model.GameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGame(@PathVariable Long id, @RequestBody GameEntity gameEntity) {
        Optional<GameEntity> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()) {
            GameEntity existingGame = optionalGame.get();
            existingGame.setGame_name(gameEntity.getGame_name());
            existingGame.setGame_developer(gameEntity.getGame_developer());
            existingGame.setGame_publisher(gameEntity.getGame_publisher());
            existingGame.setGenre(gameEntity.getGenre());
            existingGame.setPublisherEntity(gameEntity.getPublisherEntity());
            gameRepository.save(existingGame);
            return ResponseEntity.ok(existingGame);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping("/")
    public String getIndexPage(){ return "index"; }

    @RequestMapping("/AddGameForm")
    public String addGameForm(){ return "addGame";}

}
