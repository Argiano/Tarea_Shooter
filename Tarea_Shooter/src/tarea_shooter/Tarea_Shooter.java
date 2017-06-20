/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_shooter;

import java.awt.*;
//import java.awt.event.*;
//import java.io.File;
import javax.swing.*;
import java.util.*;
//import javax.sound.sampled.*;
import tarea_shooter.Enemy.*;


/**
 *
 * @author e_ver
 */
public final class Tarea_Shooter{
    
    private JFrame mainFrame;
    private ArrayList<Enemy> Enemies;
    JPanel gamePanel;
    int xPos,yPos;

    public void createFrame(int width, int height, String frameText){
        mainFrame = new JFrame(frameText);
        mainFrame.setSize(width,height);
    }
    
    public void endFrame(){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
    public Tarea_Shooter(){
        createFrame(800,600,"Tarea");
        Enemies = new ArrayList<>(10);
        createEnemies(10);
        Enemies.get(2).kill();
        Enemies.get(9).kill();
        Enemies.get(6).kill();
        
        gamePanel = new drawGamePanel();
        mainFrame.add(BorderLayout.CENTER,gamePanel);
        
        endFrame();
    
    }
    public static void main(String[] args) {
        Enemy enemigo = new Enemy();
        Tarea_Shooter tarea = new Tarea_Shooter();
    }
    
    public void createEnemies(int enemyNumber){
        for (int step = 0; step<enemyNumber; step++){
            Enemies.add(new Enemy((int) (Math.random() * 500) + 50,
                    (int) (Math.random() * (mainFrame.getHeight()/2))));
        }
    }
    
    class drawGamePanel extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            for (int step = 0; step<Enemies.size() && Enemies.get(step)!= null; step++){
                if (Enemies.get(step).isAlive()){
                    xPos = (int) Enemies.get(step).getX() - 15;
                    yPos = (int) Enemies.get(step).getY() - 15;
                    g.drawOval(xPos, yPos, 30, 30);
                    String eNumber = Integer.toString(step);
                    g.drawString(eNumber, xPos + 15, yPos + 15);
                }
            }
        }
    }
    
}