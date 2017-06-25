/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_shooter.libs;

/**
 *
 * @author e_ver
 */
public class Punto2D{
  private double x,y;
  
  public Punto2D(){
  }
  
  public Punto2D(double coord){
    this(coord,coord);
  }
  
  public Punto2D(double x, double y){
    this.x = x;
    this.y = y;
  }
  
  public double getX(){
    return x;
  }
  
  public double getY(){
    return y;
  }
  
  public void setX(double x){
    this.x = x;
  }
  
  public void setY(double y){
    this.y = y;
  }
  
  void mover(double dx, double dy){
    x = x + dx;
    y = y + dy;
  }
  
}