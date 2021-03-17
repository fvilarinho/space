package br.com.concepting.space.helpers;

import java.awt.*;

public abstract class Interceptable extends ImageObject{
    private Interceptable interceptedBy;
    private double maximumDamageHandled;
    private double maximumDamage;
    private double damage;
    private boolean destroyed;
    
    public Interceptable(){
        super();
    }

    public Interceptable(Space space){
        super(space);
    }
    
    public boolean isDestroyed(){
        return this.destroyed;
    }
    
    protected void setDestroyed(Boolean destroyed){
        this.destroyed = destroyed;
    }
    
    public Double getMaximumDamageHandled(){
        return this.maximumDamageHandled;
    }
    
    protected void setMaximumDamageHandled(Double maximumDamageHandled){
        this.maximumDamageHandled = maximumDamageHandled;
    }
    
    public Double getMaximumDamage(){
        return this.maximumDamage;
    }
    
    protected void setMaximumDamage(Double maximumDamage){
        this.maximumDamage = maximumDamage;
    }
    
    public Double getDamage(){
        return this.damage;
    }
    
    protected void setDamage(Double damage){
        this.damage = damage;
    }
    
    public Interceptable getInterceptedBy(){
        return this.interceptedBy;
    }
    
    protected void setInterceptedBy(Interceptable interceptedBy){
        this.interceptedBy = interceptedBy;
    
        this.setDamage(this.getDamage() + interceptedBy.getMaximumDamage());
    }
    
    protected void initialize(){
        super.initialize();
        
        this.setMaximumDamageHandled(maximumDamageHandled);
        this.setMaximumDamage(maximumDamage);
        this.setDamage(0d);
        this.setDestroyed(false);
    }
    
    protected boolean wasIntercepted(Interceptable interceptable){
        if(this.isVisible()){
            Point position = super.getPosition();
            Dimension size = super.getSize();
            int x1 = (int) position.getX();
            int y1 = (int) position.getY();
            int width1 = (int) size.getWidth();
            int height1 = (int) size.getHeight();
            Rectangle r1 = new Rectangle(x1, y1, width1, height1);
    
            position = interceptable.getPosition();
            size = interceptable.getSize();
            int x2 = (int) position.getX();
            int y2 = (int) position.getY();
            int width2 = (int) size.getWidth();
            int height2 = (int) size.getHeight();
            Rectangle r2 = new Rectangle(x2, y2, width2, height2);
            Boolean intercepted = (r1.intersects(r2));
    
            if(intercepted){
                this.setInterceptedBy(interceptable);
                interceptable.setInterceptedBy(this);
            }
    
            return intercepted;
        }
        
        return false;
    }
    
    @Override
    protected void animate(){
        if(this.isDestroyed())
            this.animateOnDestroy();
        else if(this.getInterceptedBy() != null)
            this.animateOnIntercept();
        else
            super.animate();
    }
    
    protected void animateOnIntercept(){
        double damage = this.getDamage();
        double maximumDamageHandle = this.getMaximumDamageHandled();
        
        if(damage >= maximumDamageHandle)
            this.setDestroyed(true);
    }
    
    protected void animateOnDestroy(){
        this.setVisible(false);
    }
    
    protected void paint(){
        boolean isVisible = this.isVisible();
        
        if(isVisible)
            super.paint();
    }
}