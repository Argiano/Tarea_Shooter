
package tarea_shooter.libs;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * The Player class, used to create and store the player, Enemy Class and Bullet
 * Class
 * @author Eduardo Vera
 * @author Rodrigo Stevenson
 */
public abstract class InteractiveObjects{
    private Punto2D Position;
    private int ammo;
    private boolean alive;
    private int width;
    private int height;
    private Image image;
    
    public InteractiveObjects(){
        alive = true;
    }
    public InteractiveObjects(int ammo){
        this();
        this.ammo = ammo;
    }
    public InteractiveObjects(int xPos,int yPos){
        this();
        Position = new Punto2D(xPos,yPos);
    }
    public void setAmmo(int ammo){
        this.ammo = ammo;
    }
    public int getAmmo(){
        return ammo;
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
    /**
    * Sets the width and height to the same values
    *
     * @param size
     * @see setHeight()
     * @see setWidth()
    */
    public void setSize(int size){
        width = size;
        height = size;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public void setImage(String url){
        image = new ImageIcon(
            getClass().getClassLoader().getResource(url)).getImage();
    }
    public Image getImage(){
        return image;
    }
}

