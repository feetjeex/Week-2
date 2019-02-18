package com.example.mainactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.Serializable;


import static com.example.mainactivity.GameState.DRAW;


public class MainActivity extends AppCompatActivity {

    // Declaring the buttons and text block that need to be saved
    Game game;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    TextView textView;
    Button button11;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();

        // initialize members of the view so we can manipulate them later
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        textView = findViewById(R.id.textView);
        button11 = findViewById(R.id.button11);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        // Saves the text, TileState and whether the user can interact with the button
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
        outState.putCharSequence("button1", button1.getText());
        outState.putCharSequence("button2", button2.getText());
        outState.putCharSequence("button3", button3.getText());
        outState.putCharSequence("button4", button4.getText());
        outState.putCharSequence("button5", button5.getText());
        outState.putCharSequence("button6", button6.getText());
        outState.putCharSequence("button7", button7.getText());
        outState.putCharSequence("button8", button8.getText());
        outState.putCharSequence("button9", button9.getText());
        outState.putCharSequence("textView", textView.getText());
        outState.putCharSequence("button11", button11.getText());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        // Sets the text, TileState and whether the user can interact with the button on restore
        super.onRestoreInstanceState(savedInstanceState);
        game = (Game) savedInstanceState.getSerializable("game");
        GameOver(game.won());
        button1.setText(savedInstanceState.getCharSequence("button1"));
        button2.setText(savedInstanceState.getCharSequence("button2"));
        button3.setText(savedInstanceState.getCharSequence("button3"));
        button4.setText(savedInstanceState.getCharSequence("button4"));
        button5.setText(savedInstanceState.getCharSequence("button5"));
        button6.setText(savedInstanceState.getCharSequence("button6"));
        button7.setText(savedInstanceState.getCharSequence("button7"));
        button8.setText(savedInstanceState.getCharSequence("button8"));
        button9.setText(savedInstanceState.getCharSequence("button9"));
        textView.setText(savedInstanceState.getCharSequence("textView"));
        button11.setText(savedInstanceState.getCharSequence("button11"));

    }

    // Method to check which button the user clicked, and pass this value to the choose method
    // Afterwards, saves the result of game.choose in the TileState 'state'
    // Also calls the method 'buttonUpdater', and the method 'GameOver' to check if the game is won
    // by the current user
    public void tileClicked(View view) {
        int id = view.getId();
        TileState state = null;
        switch(view.getId()){
            case R.id.button1:
                state = game.choose(0, 0);
                break;

            case R.id.button2:
                state = game.choose(0, 1);
                break;

            case R.id.button3:
                state = game.choose(0, 2);
                break;

            case R.id.button4:
                state = game.choose(1, 0);
                break;

            case R.id.button5:
                state = game.choose(1, 1);
                break;

            case R.id.button6:
                state = game.choose(1, 2);
                break;

            case R.id.button7:
                state = game.choose(2, 0);
                break;

            case R.id.button8:
                state = game.choose(2, 1);
                break;

            case R.id.button9:
                state = game.choose(2, 2);
                break;

        }
        buttonUpdater(state, id);
        GameOver(game.won());
    }

    // Method to update the button the user pressed to either a 'X' or an 'O'
    public void buttonUpdater(TileState state, int id) {
        Button btn = findViewById(id);
        switch(state) {
            case CROSS:
                btn.setText("X");
                game.counter ++;
                break;

            case CIRCLE:
                btn.setText("O");
                game.counter ++;
                break;

            case INVALID:
                break;
        }
    }

    // Method to disable user input on all buttons except for the reset button, after one of
    // the users wins the game, or a draw is reached
    // Uses a for loop to set all buttons (except the reset button) to disabled
    // Also displays a small text box indicating the stage of the game (in progress, draw, or won by
    // either player)
    public void GameOver(GameState state) {
        TextView text1;
        text1 = findViewById(R.id.textView);
        switch(state) {
            case PLAYER_ONE: {
                text1.setText("Player one has won!");
                text1.setVisibility(View.VISIBLE);
                ViewGroup layout = (ViewGroup)findViewById(R.id.gridLayout);
                for (int i = 0; i < layout.getChildCount(); i++) {
                    View child = layout.getChildAt(i);
                    if(child instanceof Button) {
                        Button button = (Button) child;
                        button.setEnabled(false);
                    }
                }
                break;
            }

            case PLAYER_TWO: {
                text1.setText("Player two has won!");
                text1.setVisibility(View.VISIBLE);
                ViewGroup layout = (ViewGroup)findViewById(R.id.gridLayout);
                for (int i = 0; i < layout.getChildCount(); i++) {
                    View child = layout.getChildAt(i);
                    if(child instanceof Button) {
                        Button button = (Button) child;
                        button.setEnabled(false);
                    }
                }
                break;
            }

            case IN_PROGRESS: {
                text1.setText("Game in progress!");
                text1.setVisibility(View.VISIBLE);
                break;
            }

            case DRAW: {
                text1.setText("It's a draw!");
                text1.setVisibility(View.VISIBLE);
                ViewGroup layout = (ViewGroup)findViewById(R.id.gridLayout);
                for (int i = 0; i < layout.getChildCount(); i++) {
                    View child = layout.getChildAt(i);
                    if(child instanceof Button) {
                        Button button = (Button) child;
                        button.setEnabled(false);
                    }
                }
                break;
            }
        }
    }

    // Method to reset the game after a user presses the reset button
    public void resetClicked(View view) {
        setContentView(R.layout.activity_main);
        game = new Game();

    }
}
