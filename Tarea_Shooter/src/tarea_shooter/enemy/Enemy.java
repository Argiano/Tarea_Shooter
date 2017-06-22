/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_shooter.enemy;
import tarea_shooter.player.Player;

/**
 *
 * @author e_ver
 */
public class Enemy extends Player{
    private int size;
    
    public Enemy(){
    }
    public Enemy(int xPos, int yPos){
        super.setAlive(true);
        super.setPosition(xPos,yPos);
    }

}
