package com.wisnuprsj.tictactoeapp.model;

import lombok.Data;

import java.util.HashMap;

@Data
public class Session {

    private boolean gameSession = false;
    private boolean startPlay = false;
    private boolean winnerExists = false;
    private boolean playerTurn = false;
    private int filledBox = 0;
    private Player playerOne;
    private Player playerTwo;
    private int dimension;
    private HashMap<String, BoxIndex> boxIndex = new HashMap<>();
    private Box[][] grid;
}
