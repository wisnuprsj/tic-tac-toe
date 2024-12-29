package com.wisnuprsj.tictactoeapp.config.games;

import com.wisnuprsj.tictactoeapp.config.variable.ApplicationConstant;
import com.wisnuprsj.tictactoeapp.model.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameSession {

    @Bean(ApplicationConstant.GAME_SESSION)
    public Session gameSession() {
        return new Session();
    }
}
