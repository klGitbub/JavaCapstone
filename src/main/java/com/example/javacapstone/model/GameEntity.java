package com.example.javacapstone.model;

import javax.persistence.*;

public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "game_name")
    private String game_name;

    @Column(name = "game_publisher")
    private String game_publisher;

    @Column(name = "game_developer")
    private String game_developer;

    @Column(name = "genre")
    private String genre;

    @Column(name = "game_platform")
    private String game_platform;


}
