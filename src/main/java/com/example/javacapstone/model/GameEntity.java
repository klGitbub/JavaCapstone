package com.example.javacapstone.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "game_name")
    private String game_name;


    @Column(name = "game_developer")
    private String game_developer;

    @Column(name = "genre")
    private String genre;

    @Column(name = "game_platform")
    private String game_platform;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublisherEntity publisher;

    @ManyToMany
    Set<PlayerEntity> players = new HashSet<>();

    @Transient
    private Long publisher_id;

    @Transient
    public Long getPublisher_id() { return publisher_id; }

    public void setPublisher_id(Long publisher_id) { this.publisher_id = publisher_id; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_developer() {
        return game_developer;
    }

    public void setGame_developer(String game_developer) {
        this.game_developer = game_developer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGame_platform() {
        return game_platform;
    }

    public void setGame_platform(String game_platform) {
        this.game_platform = game_platform;
    }

    public PublisherEntity getPublisherEntity() {
        return publisher;
    }

    public void setPublisherEntity(PublisherEntity publisherEntity) {
        this.publisher = publisherEntity;
    }

    public Set<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerEntity> players) {
        this.players = players;
    }

    public void addPlayer(PlayerEntity player) {
        this.players.add(player);
    }


}
