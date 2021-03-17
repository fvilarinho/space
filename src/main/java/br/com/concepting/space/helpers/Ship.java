package br.com.concepting.space.helpers;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Ship extends Controlable{
    private static final double MAX_DAMAGE_HANDLED = 100d;
    private static final double MAX_DAMAGE = 100d;

    public Ship(Space space){
        super(space);
    }
    
    private int upThrottle;
    private int forwardThrottle;
    
    protected void initialize(){
        super.initialize();
    
        super.setMaximumDamageHandled(MAX_DAMAGE_HANDLED);
        super.setMaximumDamage(MAX_DAMAGE);
        
        this.setUpThrottle(0);
        this.setForwardThrottle(0);
        
        Space space = super.getSpace();
        Dimension size = super.getSize();
        int maxHeight = space.getMaxHeight();
        int height = (int)size.getHeight();
        int x = 0;
        int y = (maxHeight - height) / 2;
        
        super.setPosition(new Point(x, y));
    }
    
    public int getUpThrottle(){
        return this.upThrottle;
    }
    
    protected void setUpThrottle(int upThrottle){
        this.upThrottle = upThrottle;
    }
    
    public int getForwardThrottle(){
        return this.forwardThrottle;
    }
    
    protected void setForwardThrottle(int forwardThrottle){
        this.forwardThrottle = forwardThrottle;
    }
    
    @Override
    protected void addControls(){
        Space space = super.getSpace();
        
        space.addKeyListener(new ControlListener());
    }
    
    @Override
    protected void animateWhenVisible(){
        move();
    }
    
    @Override
    protected void animateOnIntercept(){
        move();
        
        super.animateOnIntercept();
    }
    
    private void move(){
        Space space = super.getSpace();
        Point position = super.getPosition();
        Dimension size = super.getSize();
        int maxWidth = space.getMaxWidth();
        int maxHeight = space.getMaxHeight();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        int upThrottle = this.getUpThrottle();
        int forwardThrottle = this.getForwardThrottle();
        int x = (int)position.getX() + forwardThrottle;
        int y = (int)position.getY() + upThrottle;
    
        if((x + width) >= maxWidth){
            x = maxWidth - width;
        
            this.setForwardThrottle(0);
        }
    
        if(x <= 0){
            x = 0;
        
            this.setForwardThrottle(0);
        }
    
        if((y + height) >= maxHeight){
            y = maxHeight - height;
        
            this.setUpThrottle(0);
        }
    
        if(y <= 0){
            y = 0;
        
            this.setUpThrottle(0);
        }
    
        position.setLocation(x, y);
    }
    
    protected void fire(){
        Space space = super.getSpace();
        List<Object> objects = space.getObjects();
        
        objects.add(new Photon(this));
    }
    
    protected void moveUp(){
        int upThrottle = this.getUpThrottle();
        
        upThrottle--;
        
        this.setUpThrottle(upThrottle);
    }
    
    protected void moveDown(){
        int upThrottle = this.getUpThrottle();
    
        upThrottle++;
    
        this.setUpThrottle(upThrottle);
    }
    
    protected void moveLeft(){
        int forwardThrottle = this.getForwardThrottle();
    
        forwardThrottle--;
    
        this.setForwardThrottle(forwardThrottle);
    }
    
    protected void moveRight(){
        int forwardThrottle = this.getForwardThrottle();
    
        forwardThrottle++;
    
        this.setForwardThrottle(forwardThrottle);
    }

    class ControlListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e){
        }
        
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
                Ship.this.moveUp();
            else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
                Ship.this.moveDown();
            else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
                Ship.this.moveLeft();
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
                Ship.this.moveRight();
            else if(e.getKeyCode() == KeyEvent.VK_SPACE)
                Ship.this.fire();
        }
        
        @Override
        public void keyReleased(KeyEvent e){
        }
    }
}