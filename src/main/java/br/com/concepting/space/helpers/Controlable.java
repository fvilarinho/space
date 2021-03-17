package br.com.concepting.space.helpers;

import java.awt.*;

public abstract class Controlable extends Interceptable{
    public Controlable(){
        super();
    }
    
    public Controlable(Space space){
        super(space);
    }
    
    protected void initialize(){
        super.initialize();
        
        this.addControls();
    }
    
    protected void moveUp(){
        Point position = super.getPosition();
        Dimension size = super.getSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        int moveFactor = (width / height);
        int x = (int)position.getX();
        int y = (int)(position.getY() - (height / moveFactor));
        
        if(y <= 0)
            y = 0;
        
        position.setLocation(x, y);
    }
    
    protected void moveDown(){
        Point position = super.getPosition();
        Dimension size = super.getSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        int moveFactor = (width / height);
        int x = (int)position.getX();
        int y = (int)(position.getY() + (height / moveFactor));
        Space space = getSpace();
        
        if(y >= space.getMaxHeight() - height)
            y = space.getMaxHeight() - height;
        
        position.setLocation(x, y);
    }
    
    protected void moveLeft(){
        Point position = super.getPosition();
        Dimension size = super.getSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        int moveFactor = (width / height);
        int x = (int)(position.getX() - (width / moveFactor));
        int y = (int)position.getY();
        
        if(x <= 0)
            x = 0;
        
        position.setLocation(x , y);
    }
    
    protected void moveRight(){
        Point position = super.getPosition();
        Dimension size = super.getSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        int moveFactor = (width / height);
        int x = (int)(position.getX() + (width / moveFactor));
        int y = (int)position.getY();
        Space space = getSpace();
        
        if(x >= space.getMaxWidth() - width)
            x = space.getMaxWidth() - width;
        
        position.setLocation(x, y);
    }
    
    protected abstract void addControls();
}