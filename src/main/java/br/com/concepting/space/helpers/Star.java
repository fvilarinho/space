package br.com.concepting.space.helpers;

import java.awt.*;

public class Star extends SimpleObject{
    private static final int MAX_DEPTH = 3;
    private static final Color[] DEPTH_COLORS = new Color[]{Color.GRAY, Color.LIGHT_GRAY, Color.WHITE};
    private static final float[] DEPTH_SIZE = new float[]{2, 2.5f, 3};
    
    private int depth;
    
    public Star(){
        super();
    }
    
    public Star(Space space){
        super(space);
    }
    
    public int getDepth(){
        return this.depth;
    }
    
    protected void setDepth(int depth){
        this.depth = depth;
    }
    
    @Override
    public void initialize(){
        super.initialize();

        this.setDepth((int)(Math.random() * MAX_DEPTH));
        this.setColor(DEPTH_COLORS[depth]);
    }
    
    @Override
    protected void animateWhenVisible(){
        Space space = super.getSpace();
        Point position = super.getPosition();
        int speed = space.getSpeed();
        int depth = this.getDepth();
        int x = (int)position.getX() - (speed * (1 + depth));
        int y = (int)position.getY();
    
        if(x <= 0){
            x = space.getMaxWidth();
            y = (int)(Math.random() * space.getMaxHeight());
        }
    
        position.setLocation(x, y);
    }
    
    protected void paint(){
        Space space = super.getSpace();
        Graphics2D g = (Graphics2D)space.getGraphics();
        Point position = super.getPosition();
        int x = (int)position.getX();
        int y = (int)position.getY();
        Color color = super.getColor();
        
        g.setColor(color);
        g.setStroke(new BasicStroke(DEPTH_SIZE[depth]));
        g.drawLine(x, y, x, y);
    }
}
