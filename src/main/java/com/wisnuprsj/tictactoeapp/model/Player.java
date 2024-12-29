package com.wisnuprsj.tictactoeapp.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Player {
    private String uuid;
    private String name;
    private int score;
    private String label;

    public Player(String name, int score, String label) {
        this.name = name;
        this.score = score;
        this.label = label;
        uuid = UUID.randomUUID().toString();
    }

}
