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
 */
public class C4GameServer extends JFrame 
        implements C4Constants
{
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
                new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER1);
                
                //Connect to player 2
                Socket player2 = serverSocket.accept();
                
                //Append text the player 2 has joined the session
                log.append(new Date() + ": Player 2 has joined the session" + sessionNum + "\n");
                //Append IP address to text area
                log.append("Player 2's IP address :" + player2.getInetAddress().getHostAddress());
                
                //Notify player that they are player 2 
                new DataOutputStream(player2.getOutputStream()).writeInt(PLAYER2);
                
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

class HandleSess implements Runnable, C4Constants
{
    private Socket player1;
    private Socket player2;
    
    //Create 2d array and intialize the board cells
    private int[][] game = new int[8][7];
    
    //Create data stream obj to and from for each player
    private DataInputStream = fromP1;
    private DataOuputStream = toP1;
    private DataInputStream = fromP2;
    private DataOuputStream = toP2;
    
    //Create a play boolean
    private boolean play = true;
    
    public HandleSess(Socket player1, Socket player2);
    {
    this.player1 = player1;
    this.player2 = player2;
    
    //Intialize all cells
    for(int i  = 0; i < 8; i++)
    {
        for(int j = 0; j < 7; j++)
        {
            game[i][j] = 0;
        }
    }
    //Make the thread
    public void run()
    {
        try 
        {
            DataInputStream fromP1 = new DataInputStream(player1.getInputStream());
            DataOutputStream toP1 = new DataOutputStream(player1.getOutputStream());
            DataInputStream fromP2 = new DataInputStream(player2.getInputStream());
            DataOutputStream toP2 = new DataOutputStream(player2.getOutputStream());
        
            toP1.writeInt(1);
            
            //use button to place piece
            
            //check player 1 wins
            
            //
            
         }
        catch(IOException e)
        {
            System.err.println(e);
        }
    }
    
    private void sendMove(DataOutputStream out) throws IOException
    {
        
    }
    
    private boolean isFull()
    {
        for(int i  = 0; i < 8; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                if(game[i][j] == 0)
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean won()
    {
        //2.check statements to find four in a row
            
        //3.check if board is full
            
        //4.otherwise continue
            
        //do steps 2-4 for player 1 and 2
        return false;
    }
    
    
    
    
}

