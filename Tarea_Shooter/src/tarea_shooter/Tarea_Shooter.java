/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_shooter;

import tarea_shooter.enemy.Enemy;
import java.awt.*;
import java.awt.event.*;
//import java.io.File;
import javax.swing.*;
import java.util.*;
//import javax.sound.sampled.*;
import tarea_shooter.player.*;


/**
 *
 * @author e_ver
 */
public final class Tarea_Shooter{
    
    private JFrame mainFrame;
    private ArrayList<Enemy> Enemies;
    private Player player;
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
        
        Enemies = new ArrayList<>(50);
        createEnemies(50);
        
        player = new Player(Enemies.size());
        player.setPosition(mainFrame.getWidth()/2, 500);
        
        gamePanel = new drawGamePanel();
        mainFrame.add(BorderLayout.CENTER,gamePanel);
        
        mainFrame.addKeyListener(new gameKeyListener());
        endFrame();
    }
    public static void main(String[] args) {
        Enemy enemigo = new Enemy();
        Tarea_Shooter tarea = new Tarea_Shooter();
    }
    
    public void createEnemies(int enemyNumber){
        for (int step = 0; step<enemyNumber; step++){
            Enemies.add(new Enemy((int) (Math.random() * 770),
                    (int) (Math.random() * (mainFrame.getHeight()/2))));
        }
    }
    
    class drawGamePanel extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            for (int step = 0; step<Enemies.size() && Enemies.get(step)!= null; step++){
                if (Enemies.get(step).isAlive()){
                    xPos = (int) Enemies.get(step).getX();
                    yPos = (int) Enemies.get(step).getY();
                    g.drawOval(xPos, yPos, 30, 30);
                    String eNumber = Integer.toString(step);
                    g.drawString(eNumber, xPos + 15, yPos + 15);
                    
                    g.fillRect(player.getX(),player.getY(), 50, 30);
                }
            }
        }
    }
    
    class gameKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            int speed = e.getModifiers() == 1 ? 10 : 2;
            switch(e.getKeyCode()){
                case 37:
                    player.setX(player.getX()-speed);
                    break;
                case 39:
                    player.setX(player.getX()+speed);
                    break;
            }
            mainFrame.repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {}
        
    }
}