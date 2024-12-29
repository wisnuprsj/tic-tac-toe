package com.wisnuprsj.tictactoeapp.utils;

import com.wisnuprsj.tictactoeapp.model.Box;

public class CommonUtils {

    public static Box[][] transpose(Box[][] matrix) {
        Box[][] transpose = new Box[matrix.length][matrix.length];
        for(int i=0;i< matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                transpose[i][j] = matrix[j][i];
            }
        }
        return transpose;
    }

}
