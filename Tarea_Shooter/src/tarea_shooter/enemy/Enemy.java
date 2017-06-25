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
package tarea_shooter.enemy;
import tarea_shooter.libs.InteractiveObjects;

/**
 * The Enemy Class used to store and create enemies
 * @author Eduardo Vera
 * @author Rodrigo Stevenson
 */
public class Enemy extends InteractiveObjects{
    
    public Enemy(){
        super();
    }
    public Enemy(int xPos, int yPos){
        super(xPos,yPos); 
    }

}
