package cosc150.halmatest;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Board theBoard;
    int start;
    Button gameButtons[];
    boolean gameOver = false;
    boolean redFirst = true;
    boolean redTurn = true;
    boolean pieceSelected = false;
    TextView infoDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theBoard = new Board();

        gameButtons = new Button[theBoard.getBoardSize()];
        gameButtons[0] = (Button) findViewById(R.id.button0);
        gameButtons[1] = (Button) findViewById(R.id.button1);
        gameButtons[2] = (Button) findViewById(R.id.button2);
        gameButtons[3] = (Button) findViewById(R.id.button3);
        gameButtons[4] = (Button) findViewById(R.id.button4);
        gameButtons[5] = (Button) findViewById(R.id.button5);
        gameButtons[6] = (Button) findViewById(R.id.button6);
        gameButtons[7] = (Button) findViewById(R.id.button7);
        gameButtons[8] = (Button) findViewById(R.id.button8);
        gameButtons[9] = (Button) findViewById(R.id.button9);
        gameButtons[10] = (Button) findViewById(R.id.button10);
        gameButtons[11] = (Button) findViewById(R.id.button11);
        gameButtons[12] = (Button) findViewById(R.id.button12);
        gameButtons[13] = (Button) findViewById(R.id.button13);
        gameButtons[14] = (Button) findViewById(R.id.button14);
        gameButtons[15] = (Button) findViewById(R.id.button15);
        gameButtons[16] = (Button) findViewById(R.id.button16);
        gameButtons[17] = (Button) findViewById(R.id.button17);
        gameButtons[18] = (Button) findViewById(R.id.button18);
        gameButtons[19] = (Button) findViewById(R.id.button19);
        gameButtons[20] = (Button) findViewById(R.id.button20);
        gameButtons[21] = (Button) findViewById(R.id.button21);
        gameButtons[22] = (Button) findViewById(R.id.button22);
        gameButtons[23] = (Button) findViewById(R.id.button23);
        gameButtons[24] = (Button) findViewById(R.id.button24);
        gameButtons[25] = (Button) findViewById(R.id.button25);
        gameButtons[26] = (Button) findViewById(R.id.button26);
        gameButtons[27] = (Button) findViewById(R.id.button27);
        gameButtons[28] = (Button) findViewById(R.id.button28);
        gameButtons[29] = (Button) findViewById(R.id.button29);
        gameButtons[30] = (Button) findViewById(R.id.button30);
        gameButtons[31] = (Button) findViewById(R.id.button31);
        gameButtons[32] = (Button) findViewById(R.id.button32);
        gameButtons[33] = (Button) findViewById(R.id.button33);
        gameButtons[34] = (Button) findViewById(R.id.button34);
        gameButtons[35] = (Button) findViewById(R.id.button35);

        infoDisplay = (TextView) findViewById(R.id.infoDisplay);

        startNewGame();
    }

    private void startNewGame() {
        theBoard.clearBoard();

        for (int i = 0; i < gameButtons.length; i++)
        {
            if(i==0 || i ==1 || i == 2 || i ==6 || i==7 || i==12)
            {
                gameButtons[i].setBackgroundResource(R.drawable.red);
                //gameButtons[i].setText("R");
                gameButtons[i].setTag("Red");
            }
            else if(i==23 || i==28 || i==29 || i==33 || i==34 || i==35)
            {
                gameButtons[i].setBackgroundResource(R.drawable.black);
                //gameButtons[i].setText("B");
                gameButtons[i].setTag("Black");
            }
            else
            {
                gameButtons[i].setBackgroundResource(R.drawable.empty);
                //gameButtons[i].setText(" ");
                gameButtons[i].setTag("Blank");
            }
            gameButtons[i].setOnClickListener(new ButtonClickListener(i));
        }
        if(redFirst)
        {
            infoDisplay.setText("Red's Turn");
            redTurn = true;
            redFirst = false;
        }
        else
        {
            infoDisplay.setText("Black's Turn");
            redTurn = false;
            redFirst = true;
        }

        start = -1;
        gameOver = false;
    }

    private void setMove(char color, int startLoc, int endLoc)
    {
        //set drawable things
        Drawable cDrawable = null;
        if(color == 'r')
        {
            cDrawable = getDrawable(R.drawable.red);
        }
        if(color == 'b')
        {
            cDrawable = getDrawable(R.drawable.black);
        }

        //
        theBoard.setMove(color, startLoc, endLoc);

        gameButtons[startLoc].setText(" ");
        gameButtons[startLoc].setTag("Blank");
        if(color == 'r')
        {
            gameButtons[endLoc].setBackgroundResource(R.drawable.red);
            //gameButtons[endLoc].setText("R");
            gameButtons[endLoc].setTag("Red");
        }
        if(color == 'b')
        {
            gameButtons[endLoc].setBackgroundResource(R.drawable.black);
            //gameButtons[endLoc].setText("B");
            gameButtons[endLoc].setTag("Black");
        }
        pieceSelected = false;
    }

    private void moveResult()
    {
        int winner = theBoard.Winner();

        if(winner == 0)
        {
            if(redTurn)
            {
                infoDisplay.setText("Black's Turn");
                redTurn = false;
            }
            else
            {
                infoDisplay.setText("Red's Turn");
                redTurn = true;
            }
        }
        else if(winner == 1)
        {
            infoDisplay.setText("Red Wins");
            gameOver = true;
        }
        else if(winner == 2)
        {
            infoDisplay.setText("Black Wins");
            gameOver = true;
        }
    }

    private class ButtonClickListener implements View.OnClickListener
    {
        int location;

        public ButtonClickListener(int loc)
        {
            location = loc;
        }

        public void onClick(View view)
        {
            if(!gameOver)
            {
                if(!pieceSelected)
                {
                    if(redTurn && gameButtons[location].getTag() == "Red")
                    {
                        gameButtons[location].setBackgroundResource(R.drawable.redselect);
                        start = location;
                        pieceSelected = true;
                    }
                    if(!redTurn && gameButtons[location].getTag() == "Black")
                    {
                        gameButtons[location].setBackgroundResource(R.drawable.blackselect);
                        start = location;
                        pieceSelected = true;
                    }
                }
                else if(pieceSelected)
                {
                    if(redTurn && gameButtons[start].getTag() == "Red")
                    {
                        gameButtons[start].setBackgroundResource(R.drawable.empty);
                        setMove('r', start, location);
                        moveResult();
                    }
                    if(!redTurn && gameButtons[start].getTag() == "Black")
                    {
                        gameButtons[start].setBackgroundResource(R.drawable.empty);
                        setMove('b', start, location);
                        moveResult();
                    }
                }
            }
        }


    }
}
