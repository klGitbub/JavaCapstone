package com.example.javacapstone.controllers;

import com.example.javacapstone.model.GameEntity;
import com.example.javacapstone.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createGame(@RequestBody GameEntity gameEntity){
        gameService.createGame(gameEntity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GameEntity> getGames(){
        return gameService.getGames();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGame(@PathVariable Long id, @RequestBody GameEntity gameEntity) {
        return gameService.updateGame(id, gameEntity);
    }

}
