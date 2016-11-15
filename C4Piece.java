/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c4app;

/**
 *
 * @author lizzy
 */
import java.awt.*;
import java.util.*;
import java.applet.Applet;

public class C4Piece
{
    static final Color red            = Color.red;
    static final Color black          = Color.black;
    static Color currentColor         = black;
    
    public static void setColor(Graphics g, Color newColor)
    {
        g.setColor(newColor);
        currentColor = newColor;
    }
    
    public static void drawLine(Graphics g, int x1, int y1, int x2, int y2)
    {
        g.drawLine(x1,y1,x2,y2);
    }
    
    public static void fillCircle(Graphics g, int centerX, int centerY, int radius)
    {
        int diameter = 2 * radius;
        g.fillOval(centerX-radius, centerY-radius, diameter, diameter);
    }
}