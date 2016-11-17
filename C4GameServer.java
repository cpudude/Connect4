
package c4app;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.util.Date;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lizzy
 * @edited by Richard
 */
public class C4GameServer extends JFrame 
implements C4Constants{
    public static int P1 = 1;
    public static int P2 = 2;
    public static int P1_WON = 1;
    public static int P2_WON = 2;

    public static void main(String[] args) 
    {
        C4GameServer c4Frame = new C4GameServer();
    }
    
    public C4GameServer()
    {
        //Create text area
        JTextArea log = new JTextArea();
        
        //Create a scroll pane for text area
        JScrollPane scroll = new JScrollPane(log);
        
        //Add a scroll pane to the frame
        add(scroll, BorderLayout.CENTER);
        
        //Display the Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("ConnectFourServer");
        setVisible(true);
        
        try
        {
            //Create a server socket
            ServerSocket serverSocket= new ServerSocket(8000);
            log.append(new Date() + ": Server has started at socket 8000.\n");
            
            //Number a session
            int sessionNum = 1;
            
            //Create connect four game session
            while(true)
            {
                //Append text to text area with time stamp
                log.append(new Date() + ": Waiting for another player to join the session " + sessionNum + "\n");
                
                //Connect to player 1
                Socket player1 = serverSocket.accept();
                
                //Append text the player 1 has joined the session
                log.append(new Date() + ": Player 1 has joined the session" + sessionNum + "\n");
                //Append IP address to text area
                log.append("Player 1's IP address :" + player1.getInetAddress().getHostAddress());
                
                //Notify player that they are player 1 
                new DataOutputStream(player1.getOutputStream()).writeInt(P1);
                
                //Connect to player 2
                Socket player2 = serverSocket.accept();
                
                //Append text the player 2 has joined the session
                log.append(new Date() + ": Player 2 has joined the session" + sessionNum + "\n");
                //Append IP address to text area
                log.append("Player 2's IP address :" + player2.getInetAddress().getHostAddress());
                
                //Notify player that they are player 2 
                new DataOutputStream(player2.getOutputStream()).writeInt(P2);
                
                //Display this session and increment the session number
                sessionNum++;
                log.append(new Date() + ": Start a thread for this session" + sessionNum + "\n");
                
                HandleSess control = new HandleSess(player1, player2);
                
                new Thread(control).start();
            }   
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
    }      
}

class HandleSess implements Runnable, C4Constants{
    private Socket player1;
    private Socket player2;
   
    //Create data stream obj to and from for each player
    //added gameBoardarray - Richard
    private int [][] gameBoard = new int [6][7];
    private DataInputStream fromP1;
    private DataOutputStream toP1;
    private DataInputStream fromP2;
    private DataOutputStream toP2;
    
    
    //Create a play boolean
    private boolean play = true;
    
    public HandleSess(Socket player1, Socket player2)
    {
    this.player1 = player1;
    this.player2 = player2;
    
    
    //Intialize all cells
    for(int i  = 0; i < 6; i++)
    {
        for(int j = 0; j < 7; j++)
        {
            gameBoard[i][j] = 0;
        }
    }
    
   }
    
    //Make the thread
    public void run(){
        
        try 
        {
            DataInputStream fromP1 = new DataInputStream(player1.getInputStream());
            DataOutputStream toP1 = new DataOutputStream(player1.getOutputStream());
            DataInputStream fromP2 = new DataInputStream(player2.getInputStream());
            DataOutputStream toP2 = new DataOutputStream(player2.getOutputStream());
        
            toP1.writeInt(1);
            
            while(true)
            {
                //recieve move from player 1
                int row = fromP1.readInt();
                int col = fromP1.readInt();
                gameBoard[row][col] = 1;
                
                //Check if player 1 won
                if(won(1))
                {
                    toP1.writeInt(P1_WON);
                    toP2.writeInt(P1_WON);
                    move(toP2, col);
                    break;
                }
                else 
                {
                    toP2.writeInt(Continue);
                    
                    move(toP2, col);
                }
                
                if(won(2))
                {
                    toP1.writeInt(P2_WON);
                    toP2.writeInt(P2_WON);
                    move(toP1, col);
                    break;
                }
                else if (full())
                {
                    toP1.writeInt(Draw);
                    toP2.writeInt(Draw);
                    move(toP1, col);
                    break;
                }
                else 
                {
                    toP1.writeInt(Continue);
                    
                    move(toP1, col);
                }
            }
         }
        catch(IOException e)
        {
            System.err.println(e);
        }
    }
    
    private void move(DataOutputStream out, int col) throws IOException
    {
        out.writeInt(col); //send column index
    }
    
    private boolean full()
    {
        for(int i  = 0; i < 6; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                if(gameBoard[i][j] == ' ')
                {
                    return false;
                }
            }
        }
        //all cells are filled
        return true;
    }
    
    private boolean won(int token)
    {
        // check for a horizontal win
        for (int x=0; x<6; x++) 
        {
            for (int y=0; y<4; y++) 
            {
                if (gameBoard[x][y] != 0 && gameBoard[x][y] != -1 &&
                    gameBoard[x][y] == gameBoard[x][y+1] &&
                    gameBoard[x][y] == gameBoard[x][y+2] &&
                    gameBoard[x][y] == gameBoard[x][y+3]) 
                {
                    return true;
                }
            }
        }

        // check for a vertical win
        for (int x=0; x<3; x++) 
        {
            for (int y=0; y<7; y++) 
            {
                if (gameBoard[x][y] != 0 && gameBoard[x][y] != -1 &&
                    gameBoard[x][y] == gameBoard[x+1][y] &&
                    gameBoard[x][y] == gameBoard[x+2][y] &&
                    gameBoard[x][y] == gameBoard[x+3][y]) 
                {
                    return true;
                }
            }
        }

        // check for a diagonal win (positive slope)
        for (int x=0; x<3; x++) 
        {
            for (int y=0; y<4; y++) 
            {
                if (gameBoard[x][y] != 0 && gameBoard[x][y] != -1 &&
                    gameBoard[x][y] == gameBoard[x+1][y+1] &&
                    gameBoard[x][y] == gameBoard[x+2][y+2] &&
                    gameBoard[x][y] == gameBoard[x+3][y+3]) 
                {
                    return true;
                }
            }
        }

        // check for a diagonal win (negative slope)

        for (int x=3; x<6; x++) 
        {
            for (int y=0; y<4; y++) 
            {
                if (gameBoard[x][y] != 0 && gameBoard[x][y] != -1 &&
                gameBoard[x][y] == gameBoard[x-1][y+1] &&
                gameBoard[x][y] == gameBoard[x-2][+2] &&
                gameBoard[x][y] == gameBoard[x-3][y+3]) 
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
    
}
