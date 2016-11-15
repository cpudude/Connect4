/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c4app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 *
 * @author lizzy
 */
public class C4GameBoard extends JApplet
{
    private final int WIDTH = 1200;
    private final int HEIGHT = 670;
    
    private static JPanel main, board, sideBar, menu, game, buttonHolder, emptyPanel;
    private static JButton col1, col2, col3, col4 ,col5, col6, col7, col8;
    private static JButton play, pause, quit, changeColor;
    private static JLabel img;
    
    public C4GameBoard()
    {
        //create panels and set layouts
        main = new JPanel();
        main.setLayout(new BorderLayout());
        main.setBackground(Color.red);
        
        board = new JPanel();
        board.setLayout(new BorderLayout());
        board.setBackground(Color.blue);
        
        sideBar = new JPanel();
        sideBar.setLayout(new GridLayout(2, 1));
        
        game = new JPanel();
        game.setLayout(new FlowLayout());
        game.setBackground(Color.YELLOW);
        
        buttonHolder = new JPanel();
        buttonHolder.setLayout(new GridLayout(1, 8));
        buttonHolder.setBackground(Color.PINK);
        
        menu = new JPanel();
        menu.setLayout(new GridLayout(4, 1));
        menu.setBackground(Color.BLACK);
        
        emptyPanel = new JPanel();
        emptyPanel.setLayout(new BorderLayout());
        emptyPanel.setBackground(Color.BLACK);
        
        //create buttons
        play = new JButton("Play");
        pause = new JButton("Pause");
        changeColor = new JButton("Change Color");
        quit = new JButton("Quit");
        
        col1 = new JButton("Column 1");
        col2 = new JButton("Column 2");
        col3 = new JButton("Column 3");
        col4 = new JButton("Column 4");
        col5 = new JButton("Column 5");
        col6 = new JButton("Column 6");
        col7 = new JButton("Column 7");
        col8 = new JButton("Column 8");
        
        //add listeners to buttons
        col1.addActionListener(new ButtonListener());
        col2.addActionListener(new ButtonListener());
        col3.addActionListener(new ButtonListener());
        col4.addActionListener(new ButtonListener());
        col5.addActionListener(new ButtonListener());
        col6.addActionListener(new ButtonListener());
        col7.addActionListener(new ButtonListener());
        col8.addActionListener(new ButtonListener());
        
        //Create label that hold image
        img = new JLabel(new ImageIcon("path_to_image.png"));
        
        //add components to panels
        buttonHolder.add(col1);
        buttonHolder.add(col2);
        buttonHolder.add(col3); 
        buttonHolder.add(col4); 
        buttonHolder.add(col5); 
        buttonHolder.add(col6);
        buttonHolder.add(col7);
        buttonHolder.add(col8);
        
        menu.add(play);
        menu.add(pause);
        menu.add(changeColor);
        menu.add(quit);
        
        //add panels to eachother
        board.add(game, BorderLayout.CENTER);
        board.add(buttonHolder, BorderLayout.NORTH);
        
        sideBar.add(emptyPanel);
        sideBar.add(menu);
        
        main.add(board, BorderLayout.CENTER);
        main.add(sideBar, BorderLayout.EAST);
        
    }
    
    public void init()
    {
        getContentPane().add(main);
        setSize(WIDTH,HEIGHT);
    }

    private static class ButtonListener implements ActionListener {

        public void actonPreformed(ActionEvent event) 
        {
            if(event.getSource() == col1)
            {
                
            }
            else if(event.getSource() == col2)
            {
                
            }
            else if(event.getSource() == col3)
            {
                
            }
            else if(event.getSource() == col4)
            {
                
            }
            else if(event.getSource() == col5)
            {
                
            }
            else if(event.getSource() == col6)
            {
                
            }
            else if(event.getSource() == col7)
            {
                
            }
            else
            {
                
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
  

}

