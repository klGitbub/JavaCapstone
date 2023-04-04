package com.example.javacapstone;

import com.example.javacapstone.model.GameEntity;
import com.example.javacapstone.model.PlayerEntity;
import com.example.javacapstone.model.PublisherEntity;
import org.springframework.context.annotation.Bean;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory getFactory() {
        SessionFactory factory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(GameEntity.class)
                .addAnnotatedClass(PlayerEntity.class)
                .addAnnotatedClass(PublisherEntity.class)
                .buildSessionFactory();

        return factory;
    }
}
