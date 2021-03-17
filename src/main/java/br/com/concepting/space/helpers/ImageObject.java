package br.com.concepting.space.helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class ImageObject extends Object{
    private BufferedImage body;
    
    public ImageObject(){
        super();
    }

    public ImageObject(Space space){
        super(space);
    }
    
    protected void loadImage() throws IOException{
        String name = getClass().getSimpleName().toLowerCase().concat(".png");
        
        this.setBody(ImageIO.read(getClass().getClassLoader().getResourceAsStream(name)));
        
        int width = this.body.getWidth();
        int height = this.body.getHeight();
        
        this.setSize(new Dimension(width, height));
    }
    
    protected BufferedImage getBody(){
        return this.body;
    }
    
    protected void setBody(BufferedImage body){
        this.body = body;
    }
    
    @Override
    protected void initialize(){
        try{
            super.initialize();

            this.loadImage();
        }
        catch(IOException e){
        }
    }
    
    @Override
    protected void paint(){
        Space space = super.getSpace();
        Graphics2D g = (Graphics2D)space.getGraphics();
        Point position = super.getPosition();
        Dimension size = super.getSize();
        int x = (int)position.getX();
        int y = (int)position.getY();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
    
        g.drawImage(this.body, x, y, width, height,null);
    }
}
