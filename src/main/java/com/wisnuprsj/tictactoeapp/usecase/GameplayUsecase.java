package com.wisnuprsj.tictactoeapp.usecase;

import com.wisnuprsj.tictactoeapp.config.variable.ApplicationConstant;
import com.wisnuprsj.tictactoeapp.model.Box;
import com.wisnuprsj.tictactoeapp.model.BoxIndex;
import com.wisnuprsj.tictactoeapp.model.Player;
import com.wisnuprsj.tictactoeapp.model.Session;
import com.wisnuprsj.tictactoeapp.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameplayUsecase {

    @Autowired
    @Qualifier(ApplicationConstant.GAME_SESSION)
    private Session gameSession;

    public boolean checkWinnerPlayerOne(Player player) {

        // Check Horizontal
        for (int i=0; i < this.gameSession.getDimension(); i++) {
            Box[] row = new Box[this.gameSession.getDimension()];
            long count = 0;
            for (int j=0; j < this.gameSession.getDimension(); j++) {
                if (player.equals(this.gameSession.getGrid()[i][j].getPlayerOne())) {
                    row[j] = this.gameSession.getGrid()[i][j];
                    count++;
                }
            }
            if (count == this.gameSession.getDimension()) {
                setWinnerFlag(row);
                this.gameSession.getGrid()[i] = row;
                return true;
            }
        }

        // Check Vertical
        Box[][] transpose = CommonUtils.transpose(this.gameSession.getGrid());
        for (int i=0; i < this.gameSession.getDimension(); i++) {
            Box[] row = new Box[this.gameSession.getDimension()];
            long count = 0;
            for (int j=0; j < this.gameSession.getDimension(); j++) {
                if (player.equals(transpose[i][j].getPlayerOne())) {
                    row[j] = transpose[i][j];
                    count++;
                }
            }
            if (count == this.gameSession.getDimension()) {
                setWinnerFlag(row);
                transpose[i] = row;
                this.gameSession.setGrid(CommonUtils.transpose(transpose));
                return true;
            }
        }

        // Check Diagonal From Left
        int i = 0;
        int j = 0;
        int count = 0;
        Box[][] temp = this.gameSession.getGrid();
        while(i < this.gameSession.getDimension() && j < this.gameSession.getDimension()) {
            if (player.equals(temp[i][j].getPlayerOne())) {
                count++;
            }

            i++;
            j++;
        }

        if (count == this.gameSession.getDimension()) {
            setDiagonalLeftWinnerFlag(temp);
            this.gameSession.setGrid(temp);
            return true;
        }

        // Check Diagonal From Right
        i = 0;
        j = this.gameSession.getDimension() - 1;
        count = 0;
        temp = this.gameSession.getGrid();
        while(i < this.gameSession.getDimension() && j >=0) {
            if (player.equals(temp[i][j].getPlayerOne())) {
                count++;
            }

            i++;
            j--;
        }

        if (count == this.gameSession.getDimension()) {
            setDiagonalRightWinnerFlag(temp);
            this.gameSession.setGrid(temp);
            return true;
        }

        return false;
    }

    public boolean checkWinnerPlayerTwo(Player player) {

        // Check Horizontal
        for (int i=0; i < this.gameSession.getDimension(); i++) {
            Box[] row = new Box[this.gameSession.getDimension()];
            long count = 0;
            for (int j=0; j < this.gameSession.getDimension(); j++) {
                if (player.equals(this.gameSession.getGrid()[i][j].getPlayerTwo())) {
                    row[j] = this.gameSession.getGrid()[i][j];
                    count++;
                }
            }

            if (count == this.gameSession.getDimension()) {
                setWinnerFlag(row);
                this.gameSession.getGrid()[i] = row;
                return true;
            }
        }

        // Check Vertical
        Box[][] transpose = CommonUtils.transpose(this.gameSession.getGrid());
        for (int i=0; i < this.gameSession.getDimension(); i++) {
            Box[] row = new Box[this.gameSession.getDimension()];
            long count = 0;
            for (int j=0; j < this.gameSession.getDimension(); j++) {
                if (player.equals(transpose[i][j].getPlayerTwo())) {
                    row[j] = transpose[i][j];
                    count++;
                }
            }
            if (count == this.gameSession.getDimension()) {
                setWinnerFlag(row);
                transpose[i] = row;
                this.gameSession.setGrid(CommonUtils.transpose(transpose));
                return true;
            }
        }

        // Check Diagonal From Left
        int i = 0;
        int j = 0;
        int count = 0;
        Box[][] temp = this.gameSession.getGrid();
        while(i < this.gameSession.getDimension() && j < this.gameSession.getDimension()) {
            if (player.equals(temp[i][j].getPlayerTwo())) {
                count++;
            }

            i++;
            j++;
        }

        if (count == this.gameSession.getDimension()) {
            setDiagonalLeftWinnerFlag(temp);
            this.gameSession.setGrid(temp);
            return true;
        }

        // Check Diagonal From Right
        i = 0;
        j = this.gameSession.getDimension() - 1;
        count = 0;
        temp = this.gameSession.getGrid();
        while(i < this.gameSession.getDimension() && j >=0) {
            if (player.equals(temp[i][j].getPlayerTwo())) {
                count++;
            }

            i++;
            j--;
        }

        if (count == this.gameSession.getDimension()) {
            setDiagonalRightWinnerFlag(temp);
            this.gameSession.setGrid(temp);
            return true;
        }

        return false;
    }

    public Box[][] changeBox(String boxUuid, String playerUuid) {
        BoxIndex boxIndex = this.gameSession.getBoxIndex().get(boxUuid);
        Box[][] grid = this.gameSession.getGrid();
        if (playerUuid.equals(this.gameSession.getPlayerOne().getUuid())) {
            if (grid[boxIndex.getRow()][boxIndex.getCol()].getPlayerOne() == null && grid[boxIndex.getRow()][boxIndex.getCol()].getPlayerTwo() == null) {
                grid[boxIndex.getRow()][boxIndex.getCol()].setPlayerOne(this.gameSession.getPlayerOne());
                boolean winner = checkWinnerPlayerOne(this.gameSession.getPlayerOne());
                this.gameSession.setWinnerExists(winner);
                if (winner) this.gameSession.getPlayerOne().setScore(this.gameSession.getPlayerOne().getScore() + 1);
            }
        } else {
            if (grid[boxIndex.getRow()][boxIndex.getCol()].getPlayerOne() == null && grid[boxIndex.getRow()][boxIndex.getCol()].getPlayerTwo() == null) {
                grid[boxIndex.getRow()][boxIndex.getCol()].setPlayerTwo(this.gameSession.getPlayerTwo());
                boolean winner = checkWinnerPlayerTwo(this.gameSession.getPlayerTwo());
                this.gameSession.setWinnerExists(winner);
                if (winner) this.gameSession.getPlayerTwo().setScore(this.gameSession.getPlayerTwo().getScore() + 1);
            }
        }
        this.gameSession.setGrid(grid);
        this.gameSession.setFilledBox(this.gameSession.getFilledBox() + 1);
        if (this.gameSession.getFilledBox() == (this.gameSession.getDimension() * this.gameSession.getDimension())) this.gameSession.setWinnerExists(true);
        return this.gameSession.getGrid();
    }

    public void setWinnerFlag(Box[] boxes) {
        for (int i=0; i < boxes.length; i++) {
            boxes[i].setFlagWinner(true);
        }
    }

    public void setDiagonalLeftWinnerFlag(Box[][] boxes) {
        int i = 0;
        int j = 0;
        while (i < this.gameSession.getDimension() && j < this.gameSession.getDimension()) {
            boxes[i][j].setFlagWinner(true);
            i++;
            j++;
        }
    }

    public void setDiagonalRightWinnerFlag(Box[][] boxes) {
        int i = 0;
        int j = this.gameSession.getDimension() - 1;
        while (i < this.gameSession.getDimension() && j >= 0) {
            boxes[i][j].setFlagWinner(true);
            i++;
            j--;
        }
    }

}
