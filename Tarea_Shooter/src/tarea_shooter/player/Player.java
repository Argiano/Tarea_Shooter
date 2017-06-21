/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_shooter.player;

import tarea_shooter.enemy.Punto2D;

/**
 *
 * @author e_ver
 */
public class Player{
    private Punto2D Position;
    private int ammo;
    private boolean alive;
    
    public Player(){
        alive = true;
    }
    public Player(int ammo){
        this.ammo = ammo;
    }
    public void setAmmo(int ammo){
        this.ammo = ammo;
    }
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    public boolean isAlive(){
        return alive;
    }
    public void kill(){
        alive = false;
    }
    public void setPosition(int xPos,int yPos){
        Position = new Punto2D(xPos,yPos);
    }
    public void setX(int xPos){
        Position.setX(xPos);
    }
    public void setY(int yPos){
        Position.setY(yPos);
    }
    public int getX(){
        return (int) Position.getX();
    }
    public int getY(){
        return (int) Position.getY();
    }
    
}
