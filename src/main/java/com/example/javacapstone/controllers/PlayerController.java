package com.example.javacapstone.controllers;

import com.example.javacapstone.model.PlayerEntity;
import com.example.javacapstone.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController

@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createPlayer(@RequestBody PlayerEntity playerEntity){
        playerService.createPlayer(playerEntity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerEntity> getPlayers(){
        return playerService.getPlayers();
    }

    @PostMapping(path = "{player_id}/game/{game_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPlayerToGame(@PathVariable("player_id") Long playerId, @PathVariable("game_id") Long game_id){
        playerService.addPlayerToGame(playerId, game_id);
    }

}
