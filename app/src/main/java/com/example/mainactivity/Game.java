package com.example.mainactivity;

import android.view.View;
import android.widget.Button;

public class Game {

    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private boolean playerOneTurn;
    private boolean gameOver;
    private int movesPlayed;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public TileState getTileState(int row, int column) {
        return this.board[row][column];
    }

    public GameState won() {

        return GameState.PLAYER_ONE;

        return GameState.PLAYER_TWO;

        return GameState.DRAW;

    }


    public TileState choose(int row, int column) {

        if (this.getTileState(row, column) == TileState.BLANK) {
            if (playerOneTurn) {
                this.board[row][column] = TileState.CIRCLE;
                playerOneTurn = false;
                return TileState.CIRCLE;
            }

            else {
                this.board[row][column] = TileState.CROSS;
                playerOneTurn = true;
                return TileState.CROSS;
            }
        }

        else {
            return TileState.INVALID;
        }
    }


}
