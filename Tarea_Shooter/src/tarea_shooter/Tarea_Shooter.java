/*
 *   *Tarea_Shooter* is a basic Java game.
 *   Copyright (C) <2017>  <Eduardo Vera, Rodrigo Stevenson>
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *   
 *  Contact: eduverab@outlook.com
 */
package tarea_shooter;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import tarea_shooter.player.*;
import tarea_shooter.enemy.Enemy;
import tarea_shooter.bullet.Bullet;


/**
 * @version 0.55
 * @author Eduardo Vera
 * @author Rodrigo Stevenson
 */
public final class Tarea_Shooter extends Thread{
    private JFrame firstFrame;
    private JFrame mainFrame;
    private ArrayList<Enemy> Enemies;
    private ArrayList<Bullet> Bullets;
    private Bullet bullet;
    //^areglar por yPos
    private Player player;
    JPanel gamePanel;
    JTextField introText;
    JLabel introLabel;
    int xPos,yPos;
    static int numberOfEnemies;
    boolean inputValidator=true;
    
    public void createFirstFrame(){
        firstFrame = new JFrame("Welcome to this nice full HD 4K shooter game");
        firstFrame.setSize(600,100);
        firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstFrame.setLayout(new GridLayout(2,1));
        introText = new JTextField(2);
        introLabel = new JLabel("Ingrese el número de enemigos (ingrese un "
                + "numero y presione enter para continuar):");
        introLabel.setHorizontalAlignment(JLabel.CENTER);
        
        
        firstFrame.add(introLabel);
        firstFrame.add(introText);
        introText.addKeyListener(new introKeyListener());
        firstFrame.setVisible(true);
        firstFrame.setDefaultCloseOperation(3);
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
        createFrame(800,600,"Tarea");

        Enemies = new ArrayList<>();
        player = new Player();
        bullet = new Bullet();
        bullet.kill();
        
        gamePanel = new drawGamePanel();
        mainFrame.add(BorderLayout.CENTER,gamePanel);
        
        mainFrame.addKeyListener(new gameKeyListener());
        endFrame();
        createFirstFrame();
    }
    public static void main(String[] args) {
        //Enemy enemigo = new Enemy();
        //Tarea_Shooter tarea = new Tarea_Shooter();
        Thread thread1 = new Tarea_Shooter();
        thread1.start();
    }
    
    public void createEnemies(int enemyNumber){
        for (int step = 0; step<enemyNumber; step++){
            Enemies.add(new Enemy((int) (Math.random() * 770),
                    (int) (Math.random() * (mainFrame.getHeight()/2))));
        }
    }
    
    @Override
    public void run(){  
        while(true){
            try{
                if (bullet.isAlive()){
                    //bullet.setX(player.getX());
                    bullet.setY(bullet.getY()-1);
                    if (bullet.getY() < 0)bullet.kill();
                    Thread.sleep(10);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Tarea_Shooter.class.getName()).log(Level.SEVERE, null, ex);
            }
            mainFrame.repaint();
        }
    }

    public void sortEnemies(){
        for (int step = 0; step < Enemies.size(); step++){
            for (int pos = 0; pos < Enemies.size() - 1; pos++){
                if(Enemies.get(pos).getY() < Enemies.get(pos+1).getY()){
                    Enemy tempEn = Enemies.get(pos+1);
                    Enemies.set(pos+1, Enemies.get(pos));
                    Enemies.set(pos, tempEn);
                }
            }
        }
    }
    
    public void printEnemiesY(){
        Enemies.forEach((x) -> System.out.println(x.getY()));
    }
    
    //INNER CLASSES
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
                    if(bullet.isAlive()){
                        g.drawOval(bullet.getX(), bullet.getY(), 2, 10);
                    }
                }
            }

        }
    }
    
    class gameKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
            int speed = e.getModifiers() == 1 ? 10 : 3;
            switch(e.getKeyCode()){
                case 37:
                    //37 = LeftArrow
                    player.setX(player.getX()-speed);
                    break;
                case 39:
                    //39 = RightArrow
                    player.setX(player.getX()+speed);
                    break;
                case 32:
                    //32 = SpaceBar
                    if (!bullet.isAlive()){
                        bullet = new Bullet(player.getX(),player.getY());
                    }
                    break;

            }
            mainFrame.repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {}
        
    }
    
    class introKeyListener implements KeyListener{
        
        @Override
        public void keyTyped(KeyEvent e) {}
        
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode()==10){
                //10 = Enter
                try{
                    numberOfEnemies = Integer.parseInt(introText.getText());
                    System.out.println(numberOfEnemies);
                    if (1 > numberOfEnemies){
                        throw new Exception();
                    }
                    firstFrame.dispose();
                    inputValidator=false;
                    
                    Enemies = new ArrayList(numberOfEnemies);
                    createEnemies(numberOfEnemies);
                    sortEnemies();
                    printEnemiesY();
                    
                    player = new Player(Enemies.size());
                    player.setPosition(mainFrame.getWidth()/2, 500);
                    mainFrame.repaint();
                }
                catch(ArithmeticException errorOne){
                    introLabel.setText("Ingrese un numero valido entero de enemigos por favor:");
                }
                catch(NumberFormatException errorTwo){
                    introLabel.setText("Ingrese un numero entero por favor: ");
                }
                catch (Exception ex) {
                    introLabel.setText("Numero invalido");
                }
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {}
        
    }
}