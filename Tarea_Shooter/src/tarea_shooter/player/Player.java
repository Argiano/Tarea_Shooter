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
package tarea_shooter.player;

/**
 * The Player class, used to create and store the player, Enemy Class and Bullet
 * Class
 * @author Eduardo Vera
 * @author Rodrigo Stevenson
 */
public class Player{
    private Punto2D Position;
    private int ammo;
    private boolean alive;
    
    public Player(){
        alive = true;
    }
    public Player(int ammo){
        super();
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
