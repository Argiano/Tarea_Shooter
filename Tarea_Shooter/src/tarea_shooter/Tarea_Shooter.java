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
 * @author Eduardo Vera
 * @author Rodrigo Stevenson
 */
public final class Tarea_Shooter{
    private JFrame firstFrame;
    private JFrame mainFrame;
    private ArrayList<Enemy> Enemies;
    private Player player;
    JPanel gamePanel;
    JTextField introText;
    JLabel introLabel;
    int xPos,yPos;
    static int numberOfEnemies=0;
    boolean inputValidator=true;
    public void createFirstFrame(){
        firstFrame = new JFrame("Welcome to this nice full HD 4K shooter game");
        firstFrame.setSize(600,100);
        firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstFrame.setLayout(new GridLayout(2,1));
        introText = new JTextField(2);
        introLabel = new JLabel("    Ingrese el número de enemigos (ingrese un numero y presione enter para continuar):");
        firstFrame.add(introLabel);
        firstFrame.add(introText);
        introText.addKeyListener(new introKeyListener());
        firstFrame.setVisible(true);
        
    }
    public void createFrame(int width, int height, String frameText){
        mainFrame = new JFrame(frameText);
        mainFrame.setSize(width,height);
    }
    
    public void endFrame(){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
    public Tarea_Shooter(){
        createFirstFrame();
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
            Enemies.add(new Enemy((int) (Math.random() * 770),(int) (Math.random() * (mainFrame.getHeight()/2))));
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
                /*case 90://90='z'
                    
                    break;*/
            }
            mainFrame.repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {}
        
    }
    
    class introKeyListener implements KeyListener{
        
        public void keyTyped(KeyEvent e) {}
        
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode()==10){ //10=enter
                try{
                    numberOfEnemies = Integer.parseInt(introText.getText());
                    firstFrame.dispose();
                    inputValidator=false;
                }
                catch(ArithmeticException errorOne){
                    introLabel.setText("Ingrese un numero valido entero de enemigos por favor:");
                }
                catch(NumberFormatException errorTwo){
                    introLabel.setText("Ingrese un numero entero por favor: ");
                }
            }
        }
        
        public void keyReleased(KeyEvent e) {}
        
    }
}