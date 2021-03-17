package br.com.concepting.space.helpers;

import java.awt.*;

public class Asteroid extends Interceptable{
    private static final int MAX_SPEED = 3;
    private static final double MAX_DAMAGE = 100d;
    private static final double MAX_DAMAGE_HANDLED = 10d;
    
    private int speed;
    
    public Asteroid(){
        super();
    }
    
    public Asteroid(Space space){
        super(space);
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

        Space space = super.getSpace();
        int maxWidth = space.getMaxWidth();
        int maxHeight = space.getMaxHeight();
        int x = maxWidth + (int)(Math.random() * maxWidth);
        int y = (int)(Math.random() * maxHeight);
        int speed = MAX_SPEED + (int)(Math.random() * MAX_SPEED);

        this.setSpeed(speed);
        this.setPosition(new Point(x, y));
    }
    
    protected void animateWhenVisible(){
        Space space = super.getSpace();
        Point position = super.getPosition();
        Dimension size = super.getSize();
        int maxWidth = space.getMaxWidth();
        int maxHeight = space.getMaxHeight();
        int speed = this.getSpeed();
        int x = (int)position.getX() - speed;
        int y = (int)position.getY();
        int width = (int)size.getWidth();
        
        if(x + width <= 0){
            x = maxWidth + (int)(Math.random() + maxWidth);
            y = (int)(Math.random() * maxHeight);
        }
    
        position.setLocation(x, y);
    }
}