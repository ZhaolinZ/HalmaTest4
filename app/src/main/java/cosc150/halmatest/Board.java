package cosc150.halmatest;

/**
 * Created by brendantecce on 11/16/17.
 */

public class Board {

    Piece[] pieces;
    int boardSize = 36;

    public Board()
    {
        pieces = new Piece[boardSize];
        for(int i=0; i<boardSize; i++)
        {
            pieces[i] = new Piece(' ');
        }

        clearBoard();
    }

    public void clearBoard()
    {
        for(int i = 0; i<boardSize; i++)
        {
            if(i==0 || i ==1 || i == 2 || i ==6 || i==7 || i==12)
            {
                pieces[i].setColor('r');
            }
            else if(i==23 || i==28 || i==29 || i==33 || i==34 || i==35)
            {
                pieces[i] = new Piece('b');
            }
            else
            {
                pieces[i] = new Piece(' ');
            }
        }
    }

    public void setMove(char c, int start, int end)
    {
        pieces[start].setColor(' ');
        pieces[end].setColor(c);
    }

    public int Winner()
    {
      if(pieces[0].getColor() == 'b' && pieces[1].getColor() == 'b' && pieces[2].getColor() == 'b'
              && pieces[6].getColor() == 'b' && pieces[7].getColor()== 'b' && pieces[12].getColor() == 'b')
      {
          return 2;
      }
      else if(pieces[23].getColor() == 'r' && pieces[28].getColor() == 'r' && pieces[29].getColor() == 'r'
            && pieces[33].getColor() == 'r' && pieces[34].getColor()== 'r' && pieces[35].getColor() == 'r')
      {
         return 1;
      }
      else
      {
          return 0;
      }
    }


    public int getBoardSize(){return boardSize;}
}
