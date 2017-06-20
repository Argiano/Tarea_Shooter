/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_shooter.Enemy;

/**
 *
 * @author e_ver
 */
public class Enemy{
    private Punto2D Position;
    private boolean alive;
    private int size;
    
    public Enemy(){
        alive = true;
    }
    public Enemy(int xPos, int yPos){
        alive = true;
        Position = new Punto2D(xPos, yPos);
    }
    public int getX(){
        return (int) Position.getX();
    }
    public int getY(){
        return (int) Position.getY();
    }
    public boolean isAlive(){
        return alive;
    }
    public void kill(){
        alive = false;
    }
    
}
