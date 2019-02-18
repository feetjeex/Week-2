package com.example.mainactivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

// Class which implement the main functionality of the game
public class Game implements Serializable {

    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private boolean playerOneTurn;
    private boolean gameOver;
    private int movesPlayed;
    public int counter = 0;


    // Resets all the tiles/buttons in the game to BLANK
    // Sets the turn to player one
    // Sets gameOver to false
    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    // Method to get the TileState of a certain button
    public TileState getTileState (int row, int column) {
        return this.board[row][column];
    }

    // Method to check whether any user has three of their marks in a row, column, or diagonally
    // Returns the GameState of which player has won
    // Otherwise, returns that the game is in progress, or if sufficient moves have been made,
    // that the game resulted in a draw
    public GameState won() {
        if (getTileState(0, 0) == getTileState(0,1) && getTileState(0,2) == getTileState(0,1) && getTileState(0,1) != TileState.BLANK) {
            if (!playerOneTurn) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }

            else {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
        }

        else if (getTileState(1, 0) == getTileState(1,1) && getTileState(1,2) == getTileState(1,1) && getTileState(1,1) != TileState.BLANK) {
            if (!playerOneTurn) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }

            else {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
        }

        else if (getTileState(2, 0) == getTileState(2,1) && getTileState(2,2) == getTileState(2,1) && getTileState(2,1) != TileState.BLANK) {
            if (!playerOneTurn) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }

            else {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
        }

        else if (getTileState(0, 0) == getTileState(1,0) && getTileState(1,0) == getTileState(2,0) && getTileState(1,0) != TileState.BLANK) {
            if (!playerOneTurn) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }

            else {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
        }

        else if (getTileState(0, 1) == getTileState(1,1) && getTileState(1,1) == getTileState(2,1) && getTileState(1,1) != TileState.BLANK) {
            if (!playerOneTurn) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }

            else {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
        }

        else if (getTileState(0, 2) == getTileState(1,2) && getTileState(1,2) == getTileState(2,2) && getTileState(1,2) != TileState.BLANK) {
            if (!playerOneTurn) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }

            else {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
        }

        else if (getTileState(0, 0) == getTileState(1,1) && getTileState(1,1) == getTileState(2,2) && getTileState(1,1) != TileState.BLANK) {
            if (!playerOneTurn) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }

            else {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
        }

        else if (getTileState(0, 2) == getTileState(1,1) && getTileState(1,1) == getTileState(2,0) && getTileState(1,1) != TileState.BLANK) {
            if (!playerOneTurn) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }

            else {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
        }

        else if (counter >= 9) {
            return GameState.DRAW;

        }

        else {
            return GameState.IN_PROGRESS;
        }

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
