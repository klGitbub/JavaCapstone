package com.example.javacapstone;

import org.springframework.context.annotation.Bean;
import org.hibernate.SessionFactory;

public class HibernateConfig {

    @Bean
    public SessionFactory getFactory() {
        SessionFactory factory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        return factory;
    }
}
