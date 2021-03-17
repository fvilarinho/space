package br.com.concepting.space.helpers;

import java.awt.*;

public abstract class SimpleObject extends Object{
    private Color color;
    
    public SimpleObject(){
        super();
    }
    
    public SimpleObject(Space space){
        super(space);
    }
    
    public Color getColor(){
        return this.color;
    }
    
    protected void setColor(Color color){
        this.color = color;
    }
}
