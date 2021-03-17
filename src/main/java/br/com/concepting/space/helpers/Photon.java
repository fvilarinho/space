package br.com.concepting.space.helpers;

import java.awt.*;

public class Photon extends Interceptable{
    private static final int MAX_SPEED = 5;
    private static final double MAX_DAMAGE_HANDLED = 10d;
    private static final double MAX_DAMAGE = 10d;
    
    private Ship ship;
    private int speed;
    
    public Photon(Ship ship){
        super();
        
        Space space = ship.getSpace();
        
        super.setSpace(space);
        
        this.setShip(ship);
        
        this.initialize();
    }
    
    public Ship getShip(){
        return ship;
    }
    
    protected void setShip(Ship ship){
        this.ship = ship;
    }
    
    public int getSpeed(){
        return this.speed;
    }
    
    protected void setSpeed(int speed){
        this.speed = speed;
    }
    
    protected void initialize(){
        super.initialize();
    
        super.setMaximumDamageHandled(MAX_DAMAGE_HANDLED);
        super.setMaximumDamage(MAX_DAMAGE);

        Ship ship = this.getShip();
        Point position = ship.getPosition();
        Dimension size = ship.getSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        int x = (int)(position.getX() + width);
        int y = (int)(position.getY() + (height / 2));
        int speed = MAX_SPEED;

        this.setSpeed(speed);
        this.setPosition(new Point(x, y));
    }
    
    protected void animateWhenVisible(){
        Space space = super.getSpace();
        Point position = super.getPosition();
        int maxWidth = space.getMaxWidth();
        int speed = this.getSpeed();
        int x = (int)position.getX() + speed;
        int y = (int)position.getY();
        
        if(x >= maxWidth)
            super.setDestroyed(true);
    
        position.setLocation(x, y);
    }
}