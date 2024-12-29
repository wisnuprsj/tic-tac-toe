package com.wisnuprsj.tictactoeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Box {

    private String uuid;
    private Player playerOne;
    private Player playerTwo;
    private boolean flagWinner = false;

    public Box() {
        this.uuid = UUID.randomUUID().toString();
    }

}
