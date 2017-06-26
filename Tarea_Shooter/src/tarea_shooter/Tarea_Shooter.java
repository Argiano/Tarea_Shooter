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
 * @version 0.7
 * @author Eduardo Vera
 * @author Rodrigo Stevenson
 */
public final class Tarea_Shooter extends Thread{
    private JFrame firstFrame;
    private JFrame mainFrame;
    private ArrayList<Enemy> Enemies;
    //private ArrayList<Bullet> Bullets;
    private Bullet bullet;
    private Player player;
    private JPanel gamePanel;
    private JTextField introText;
    private JLabel introLabel;
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
        firstFrame.setAlwaysOnTop(true);
        firstFrame.setResizable(false);
        firstFrame.setDefaultCloseOperation(3);
    }
    
    public void createFrame(int width, int height, String frameText){
        mainFrame = new JFrame(frameText);
        mainFrame.setSize(width,height);
    }
    
    public void endFrame(){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

    public Tarea_Shooter(){
        createFirstFrame();
        createFrame(800,600,"Tarea");

        Enemies = new ArrayList<>();
        player = new Player();
        bullet = new Bullet();
        bullet.kill();
        
        gamePanel = new drawGamePanel();
        mainFrame.add(BorderLayout.CENTER,gamePanel);
        
        mainFrame.addKeyListener(new gameKeyListener());
        endFrame();
        
    }
    public static void main(String[] args) {
        Thread thread1 = new Tarea_Shooter();
        thread1.start();
    }
    
    public void createEnemies(int enemyNumber){
        for (int step = 0; step<enemyNumber; step++){
            Enemies.add(new Enemy((int) (Math.random() * 770),
                    (int) (Math.random() * (mainFrame.getHeight()/2))));
            Enemies.get(step).setImage("tarea_shooter/images/enemy.png");
            Enemies.get(step).setSize(30);
        }
    }
    
    @Override
    public void run(){
        while(true){
            try{
                mainFrame.setFocusable(!firstFrame.isShowing());
                if (bullet.isAlive()){
                    bullet.setY(bullet.getY()-1);
                    if (bullet.getHitY() < 0){
                        bullet.kill();
                        if (player.getAmmo()==0){
                            createFirstFrame();
                        }
                    }
                    killEnemy();
                    Thread.sleep(5);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Tarea_Shooter.class.getName()).log(Level.SEVERE, null, ex);
            }
            mainFrame.repaint();
        }
    }
    
    public void killEnemy(){
        for (int step = 0; step < Enemies.size(); step++){
            if(Enemies.get(step).isAlive()){
                if (bullet.getHitY() == 
                        (Enemies.get(step).getY()+Enemies.get(step).getHeight())) {
                    int diff = bullet.getHitX() - Enemies.get(step).getX();
                    if(diff > 0 && Math.abs(diff)< 30){
                        Enemies.get(step).kill();
                        bullet.kill();
                        if(!Enemies.isEmpty() && enemiesDead()){
                            createFirstFrame();
                        }
                    }
                }
            }
        }
    }

    public void sortEnemies(){
        Enemies.forEach((_item) -> {
            for (int pos = 0; pos < Enemies.size() - 1; pos++){
                if(Enemies.get(pos).getY() < Enemies.get(pos+1).getY()){
                    Enemy tempEn = Enemies.get(pos+1);
                    Enemies.set(pos+1, Enemies.get(pos));
                    Enemies.set(pos, tempEn);
                }
            }
        });
    }
    
    public void printEnemiesY(){
        Enemies.forEach((x) -> System.out.println(x.getY()));
    }
    public boolean enemiesDead(){
        boolean allDead = false;
        for (int step = 0; step < Enemies.size(); step++){
            if(Enemies.get(step).isAlive()){
                allDead = true;
                }
            }
        return !allDead;   
    }
    
    //INNER CLASSES
    class drawGamePanel extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            int imgWidth, imgHeight;
            for (int step = 0; step<Enemies.size() && Enemies.get(step)!= null; step++){
                if (Enemies.get(step).isAlive()){
                    xPos = (int) Enemies.get(step).getX();
                    yPos = (int) Enemies.get(step).getY();
                    //g.drawOval(xPos, yPos, 30, 30);
                    imgWidth = Enemies.get(step).getWidth();
                    imgHeight = Enemies.get(step).getHeight();
                    g.drawImage(Enemies.get(step).getImage(), xPos, yPos,
                            imgWidth, imgHeight,gamePanel);

                    g.fillRect(player.getX(),player.getY(),player.getWidth(),
                            player.getHeight());
                    if(bullet.isAlive()){
                        //g.drawOval(bullet.getX(), bullet.getY(),2,10);
                        imgWidth = bullet.getWidth();
                        imgHeight = bullet.getHeight();
                        g.drawImage(bullet.getImage(), bullet.getX(),
                                bullet.getY(), imgWidth, imgHeight, mainFrame);
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
            
            int speed = e.getModifiers() == 1 ? 10 : 3;
            switch(e.getKeyCode()){
                case 37:
                    //37 = LeftArrow
                    player.setX(player.getX()-speed);
                    break;
                case 39:
                    //39 = RightArrow
                    //player.setX(player.getX()+speed);
                    break;
                case 32:
                    //32 = SpaceBar
                    if (!bullet.isAlive()){
                        bullet = new Bullet(player.getX(),player.getY());
                        bullet.setImage("tarea_shooter/images/bullet.png");
                        bullet.setSize(20);
                        player.setAmmo(player.getAmmo()-1);
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
                    
                    if (1 > numberOfEnemies){
                        throw new Exception();
                    }
                    
                    firstFrame.dispose();
                    
                    Enemies = new ArrayList(numberOfEnemies);
                    createEnemies(numberOfEnemies);
                    sortEnemies();
                    //printEnemiesY();
                    
                    player = new Player(Enemies.size());
                    player.setSize(40);
                    player.setPosition(mainFrame.getWidth() - 30, 500);
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