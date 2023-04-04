package com.example.javacapstone.controllers;

import com.example.javacapstone.model.GameEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GameController {


    @RequestMapping
    public String getIndexPage(){ return "index"; }

}
