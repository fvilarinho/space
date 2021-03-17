package br.com.concepting.space.helpers;

import java.awt.*;

public abstract class Object{
    private Point position;
    private Dimension size;
    private boolean visible;
    private Space space;
    
    public Object(){
        super();
    }
    
    public Object(Space space){
        super();
        
        this.setSpace(space);
        
        this.initialize();
    }
    
    protected void initialize(){
        Space space = this.getSpace();
        int maxWidth = space.getMaxWidth();
        int maxHeight = space.getMaxHeight();
        int x = (int)(Math.random() * maxWidth);
        int y = (int)(Math.random() * maxHeight);
        
        this.setPosition(new Point(x, y));
        this.setSize(new Dimension(1, 1));
        this.setVisible(true);
    }
    
    protected void animate(){
        if(this.isVisible())
            this.animateWhenVisible();
    }
    
    protected abstract void animateWhenVisible();
    
    protected abstract void paint();
    
    public Point getPosition(){
        return position;
    }
    
    protected void setPosition(Point position){
        this.position = position;
    }
    
    public Dimension getSize(){
        return this.size;
    }
    
    protected void setSize(Dimension size){
        this.size = size;
    }
    
    public boolean isVisible(){
        return this.visible;
    }

    protected void setVisible(Boolean visible){
        this.visible = visible;
    }
    
    public Space getSpace(){
        return this.space;
    }
    
    protected void setSpace(Space space){
        this.space = space;
    }
}
