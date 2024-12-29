package com.wisnuprsj.tictactoeapp.usecase;

import com.wisnuprsj.tictactoeapp.config.variable.ApplicationConstant;
import com.wisnuprsj.tictactoeapp.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CommonUsecase {

    @Autowired
    @Qualifier(ApplicationConstant.GAME_SESSION)
    private Session gameSession;

    public boolean checkGameSession() {
        return gameSession.isGameSession();
    }

    public boolean checkPlayer() {
        return gameSession.getPlayerOne() == null && gameSession.getPlayerTwo() == null;
    }

}
