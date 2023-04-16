package com.example.javacapstone.services;

import com.example.javacapstone.model.GameEntity;
import com.example.javacapstone.model.PlayerEntity;
import com.example.javacapstone.repositories.GameRepository;
import com.example.javacapstone.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;

    public void createPlayer(PlayerEntity  playerEntity){

        PlayerEntity newPlayer = new PlayerEntity();
        newPlayer.setFirst_name(playerEntity.getFirst_name());
        newPlayer.setLast_name(playerEntity.getLast_name());
        newPlayer.setAge(playerEntity.getAge());
        newPlayer.setGender(playerEntity.getGender());

        playerRepository.save(newPlayer);
    }

    public List<PlayerEntity> getPlayers(){
        return playerRepository.findAll();
    }

    public ResponseEntity<?> updatePlayer(Long id, PlayerEntity playerEntity){
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

    public void addPlayerToGame(Long playerId, Long gameId) {
        PlayerEntity player =  playerRepository.findById(playerId).orElseThrow(()-> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player does not exist!");
        });
        GameEntity game =  gameRepository.findById(gameId).orElseThrow(()-> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game does not exist!");
        });

        game.addPlayer(player);
        player.addGame(game);

        gameRepository.save(game);
        playerRepository.save(player);
    }
}