package project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Font;
public class tictactoe extends JFrame implements ActionListener 
{
    public tictactoe()
    {
        super.setTitle("TicTacToe");
        super.setSize(800, 800);
        GridLayout grid= new GridLayout(Board_Size,Board_Size);
        super.setLayout(grid);
        super.setResizable(false);
        Font font = new Font("Comic Sans",1,150);

        for(int row=0;row<Board_Size;row++)
        {
            for(int col=0;col<Board_Size;col++)
            {
                JButton button = new JButton("");
                buttons[row][col]=button;
                button.setFont(font);
                button.addActionListener(this);
                super.add(button);

            }
        }
        super.setVisible(true);
    }

    public static int Board_Size=3;

    public static enum GameStatus{
        Incomplete,PLayer1Wins,Player2Wins,Tie;
    }

    private JButton[][] buttons = new JButton[Board_Size][Board_Size];
    boolean crossTurn= true;

    public void actionPerformed(ActionEvent e)
    {
       JButton clickedButton=(JButton)e.getSource();
       makeMove(clickedButton);
       GameStatus gs= this.getGameStatus();
       //below if condition is important because if game is incomplete it will again allow user to click 
       if(gs==GameStatus.Incomplete)
       {
        return;
       }
       
       declareWinner(gs);
       
    }

    void makeMove(JButton clickedButton)
    {
        String btntext=clickedButton.getText();
        if(btntext.length()>0)
        {
          JOptionPane.showMessageDialog(this,"Invalid Move");
        }
        else
        {
            if(crossTurn)
            {
            clickedButton.setText("X");
            }
            else{
                clickedButton.setText("0");
            }
            crossTurn=!crossTurn;
        }
    }

    //Most Important Function LOGIC OF GAME -->
    GameStatus getGameStatus()
    {
      String text1="",text2="";
      int row=0,col=0;

      //text inside row side by side
      row=0;
      while(row<Board_Size)
      {
        col=0;
        while(col<Board_Size-1)
        {
            text1=buttons[row][col].getText();
            text2=buttons[row][col+1].getText();
            //checks if alternative row have same text or not

            if((!text1.equals(text2)) || text1.length()==0)
            {
                break;
            }
            col++;
        }
        if(col==Board_Size-1)
        {
            if(text1.equals("X"))
            {
                return GameStatus.PLayer1Wins;
            }
            else
            return GameStatus.Player2Wins;
        }
        row++;
        
    }

      //now we check for text columns down by down

      col=0;
      while(col<Board_Size)
      {
        row=0;
        while(row<Board_Size-1)
        {
            text1=buttons[row][col].getText();
            text2=buttons[row+1][col].getText();
            //checks if alternative col have same text or not

            if((!text1.equals(text2)) || text1.length()==0)
            {
                break;
            }
            row++;
        }

        if(row==Board_Size-1)
        {
            if(text1.equals("X"))
            {
                return GameStatus.PLayer1Wins;
            }
            else
            return GameStatus.Player2Wins;
        }
        col++;
    }

      //check text for diagonal 1

      row=0;
      col=0;
      while(row<Board_Size-1)
      {
        text1=buttons[row][col].getText();
        text2=buttons[row+1][col+1].getText();
        //checks if alternative diagonal have same text or not

        if((!text1.equals(text2)) || text1.length()==0)
        {
            break;
        }
        row++;
        col++;
    }
    if(row==Board_Size-1)
    {
        if(text1.equals("X"))
        {
            return GameStatus.PLayer1Wins;
        }
        else
        return GameStatus.Player2Wins;
    }

    //checks for diagonal 2

    row=Board_Size-1;
      col=0;
      while(row>0)
      {
        text1=buttons[row][col].getText();
        text2=buttons[row-1][col+1].getText();
        //checks if alternative diagonal have same text or not

        if((!text1.equals(text2)) || text1.length()==0)
        {
            break;
        }
        row--;
        col++;
    }
    if(row==0)
    {
        if(text1.equals("X"))
                {
            return GameStatus.PLayer1Wins;
        }
        else
        return GameStatus.Player2Wins;
    }

    //checks for incomplete game or tied game

    String txt="";
    for(row=0;row<Board_Size;row++)
    {
        for(col=0;col<Board_Size;col++)
        {
            txt=buttons[row][col].getText();
            if(txt.length()==0)
            {
                return GameStatus.Incomplete;
            }
        }
    }

    return GameStatus.Tie;
}

  void declareWinner(GameStatus gs)
  {
    if(gs==GameStatus.PLayer1Wins)
    {
        JOptionPane.showMessageDialog(this,"Player 1 Wins");
    }
    else if(gs==GameStatus.Player2Wins)
    {
        JOptionPane.showMessageDialog(this,"Player 2 Wins");
    }
    else if(gs==GameStatus.Tie)
    {
        JOptionPane.showMessageDialog(this,"Game is Tied");
    }
  }
}



