package ruchajoshi.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0=x,1=o
    int activePlayer = 0;
    boolean gameIsActive = true;
    // 2= unplayed
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 6}, {2, 4, 6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int tappedConter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedConter] == 2 && gameIsActive) {

            gamestate[tappedConter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;

            }

            counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);


            for (int[] winningPositions : winningPositions) {

                if (gamestate[winningPositions[0]] == gamestate[winningPositions[1]] &&
                        gamestate[winningPositions[1]] == gamestate[winningPositions[2]] &&
                        gamestate[winningPositions[0]] != 2) {


                    gameIsActive = false;

                    //someone won!!
                    String winner = "player 2";

                    if (gamestate[winningPositions[0]] == 0) {
                        winner = "player 1";
                    }

                    LinearLayout layout = findViewById(R.id.playagainLayout);
                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!!");
                    layout.setVisibility(View.VISIBLE);
                } else {

                    boolean gameIsOver = true;
                    for (int counterstate : gamestate) {

                        if (counterstate == 2) gameIsOver = false;
                    }
                    if (gameIsOver) {
                        LinearLayout layout = findViewById(R.id.playagainLayout);
                        TextView winnerMessage = findViewById(R.id.winnerMessage);
                        winnerMessage.setText("it's draw!!");
                        layout.setVisibility(View.VISIBLE);
                    }

                }

            }

        }
    }

    public void playAgain(View view) {

        gameIsActive = true;
        LinearLayout layout = findViewById(R.id.playagainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;

        for (int i = 0; i < gamestate.length; i++) {
            gamestate[i] = 2;
        }

        GridLayout gridLayout = findViewById(R.id.gridlayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }


}
