package com.wisnuprsj.tictactoeapp.usecase;

import com.wisnuprsj.tictactoeapp.config.variable.ApplicationConstant;
import com.wisnuprsj.tictactoeapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.HashMap;

@Component
public class SetPlayerUsecase {

    @Autowired
    @Qualifier(ApplicationConstant.GAME_SESSION)
    private Session gameSession;

    public Box[][] resetGameplay() {
        this.gameSession.setGrid(generateGrid());
        this.gameSession.setWinnerExists(false);
        this.gameSession.setPlayerTurn(false);
        this.gameSession.setFilledBox(0);

        return this.gameSession.getGrid();
    }

    public Box[][] setPlayerConfiguration(SetPlayer setPlayer) {
        if (this.gameSession.getPlayerOne() == null && this.gameSession.getPlayerTwo() == null) {
            this.gameSession.setPlayerOne(new Player(setPlayer.getPlayerOne(), 0, "O"));
            this.gameSession.setPlayerTwo(new Player(setPlayer.getPlayerTwo(), 0, "X"));
            this.gameSession.setDimension(setPlayer.getDimension());
        }

        this.gameSession.setGrid(generateGrid());

        return this.gameSession.getGrid();
    }

    public Box[][] generateGrid() {
        Box[][] grid = new Box[gameSession.getDimension()][gameSession.getDimension()];
        for (int i = 0; i < gameSession.getDimension(); i++) {
            for (int j = 0; j < gameSession.getDimension(); j++) {
                grid[i][j] = new Box();
                gameSession.getBoxIndex().put(grid[i][j].getUuid(), new BoxIndex(i, j));
            }
        }
        return grid;
    }

    public void resetGameSession() {
        gameSession.setGameSession(false);
        gameSession.setStartPlay(false);
        gameSession.setWinnerExists(false);
        gameSession.setFilledBox(0);
        gameSession.setPlayerTurn(false);
        gameSession.setPlayerOne(null);
        gameSession.setPlayerTwo(null);
        gameSession.setBoxIndex(new HashMap<>());
        gameSession.setGrid(null);
    }

    public String setStartSession(Model model) {
        resetGameSession();
        model.addAttribute("startGame", false);
        model.addAttribute("startPlay", false);
        model.addAttribute("setPlayer", new SetPlayer());
        return "index";
    }

    public String setStartGameModel(Model model) {
        gameSession.setGameSession(true);
        model.addAttribute("setPlayer", new SetPlayer("", "", 3));
        model.addAttribute("startGame", gameSession.isGameSession());
        model.addAttribute("startPlay", gameSession.isStartPlay());

        return "index";
    }

    public String setModelGames(Model model, Box[][] grid) {
        gameSession.setStartPlay(true);
        gameSession.setPlayerTurn(!gameSession.isPlayerTurn());
        model.addAttribute("playerOne", gameSession.getPlayerOne());
        model.addAttribute("playerTwo", gameSession.getPlayerTwo());
        model.addAttribute("playerOneName", gameSession.getPlayerOne().getName());
        model.addAttribute("playerTwoName", gameSession.getPlayerTwo().getName());
        model.addAttribute("playerOneScore", gameSession.getPlayerOne().getScore());
        model.addAttribute("playerTwoScore", gameSession.getPlayerTwo().getScore());
        model.addAttribute("startGame", gameSession.isGameSession());
        model.addAttribute("startPlay", gameSession.isStartPlay());
        model.addAttribute("dimension", gameSession.getDimension());
        model.addAttribute("maxTick", gameSession.getDimension() * gameSession.getDimension());
        model.addAttribute("winnerExist", gameSession.isWinnerExists());
        model.addAttribute("boxTicked", gameSession.getFilledBox());
        model.addAttribute("grid", grid);

        if (gameSession.isPlayerTurn()) {
            model.addAttribute("playerUuid", gameSession.getPlayerOne().getUuid());
            model.addAttribute("playerOneTurn", true);
        } else {
            model.addAttribute("playerUuid", gameSession.getPlayerTwo().getUuid());
            model.addAttribute("playerTwoTurn", true);
        }

        return "index";
    }

}
