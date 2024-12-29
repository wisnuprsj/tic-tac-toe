package com.wisnuprsj.tictactoeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetPlayer {

    private String playerOne;
    private String playerTwo;
    private int dimension;

}
