package com.example.javacapstone.controllers;

import com.example.javacapstone.model.PlayerEntity;
import com.example.javacapstone.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller

@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    public ResponseEntity<?> updatePlayer(@PathVariable Long id, @RequestBody PlayerEntity playerEntity) {
        Optional<PlayerEntity> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            PlayerEntity existingPlayer = optionalPlayer.get();
            existingPlayer.setFirst_name(playerEntity.getFirst_name());
            existingPlayer.setLast_name(playerEntity.getLast_name());
            existingPlayer.setGender(playerEntity.getGender());
            existingPlayer.setAge(playerEntity.getAge());
            playerRepository.save(existingPlayer);
            return ResponseEntity.ok(existingPlayer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/")
    public String getIndexPage(){ return "index"; }

    @RequestMapping("/AddPlayerForm")
    public String addPlayerForm(){ return "addPlayer";}

}
