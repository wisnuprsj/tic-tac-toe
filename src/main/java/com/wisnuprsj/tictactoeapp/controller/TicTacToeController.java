package com.wisnuprsj.tictactoeapp.controller;

import com.wisnuprsj.tictactoeapp.model.Box;
import com.wisnuprsj.tictactoeapp.model.SetPlayer;
import com.wisnuprsj.tictactoeapp.usecase.CommonUsecase;
import com.wisnuprsj.tictactoeapp.usecase.GameplayUsecase;
import com.wisnuprsj.tictactoeapp.usecase.SetPlayerUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tictactoe")
public class TicTacToeController {

    @Autowired
    private SetPlayerUsecase setPlayerUsecase;

    @Autowired
    private GameplayUsecase gameplayUsecase;

    @Autowired
    private CommonUsecase commonUsecase;

    @GetMapping
    public String index(Model model) {
        return setPlayerUsecase.setStartSession(model);
    }

    @GetMapping(value = "/startGame")
    public String startGame(Model model) {
        return setPlayerUsecase.setStartGameModel(model);
    }

    @RequestMapping(value = "/play", method = RequestMethod.POST)
    public String setPlayer(@ModelAttribute(value = "setPlayer") SetPlayer players, Model model) {
        Box[][] grid = setPlayerUsecase.setPlayerConfiguration(players);
        return setPlayerUsecase.setModelGames(model, grid);
    }

    @GetMapping(value = "/play")
    public String play(Model model) {
        if (commonUsecase.checkPlayer()) return "redirect:/tictactoe";
        Box[][] grid = setPlayerUsecase.resetGameplay();
        return setPlayerUsecase.setModelGames(model, grid);
    }

    @GetMapping(value = "/play/{boxUuid}/{playerUuid}")
    public String boxClick(@PathVariable("boxUuid") String boxUuid, @PathVariable("playerUuid") String playerUuid, Model model) {
        if (commonUsecase.checkPlayer()) return "redirect:/tictactoe";
        Box[][] grid = gameplayUsecase.changeBox(boxUuid, playerUuid);
        return setPlayerUsecase.setModelGames(model, grid);
    }


}
