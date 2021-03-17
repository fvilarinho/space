package br.com.concepting.space.helpers;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Space extends Canvas{
    private int maxWidth;
    private int maxHeight;
    private int maxStars;
    private int speed;
    private List<Object> objects;
    private Graphics2D g;
    
    public Space(int maxWidth, int maxHeight, int maxStars, int speed){
        super();
    
        this.setMaxWidth(maxWidth);
        this.setMaxHeight(maxHeight);
        this.setMaxStars(maxStars);
        this.setSpeed(speed);
        this.initialize();
        
        super.setFocusable(true);
        super.setBackground(Color.BLACK);
    }
    
    private void createStars(){
        int maxStars = this.getMaxStars();
        List<Object> objects = this.getObjects();
        
        if(objects == null){
            objects = new LinkedList<>();
            
            this.setObjects(objects);
        }
        
        for(int i = 0 ; i < maxStars; i++)
            objects.add(new Star(this));
    }
    
    private void createAsteroids(){
        List<Object> objects = this.getObjects();
    
        if(objects == null){
            objects = new LinkedList<>();
        
            this.setObjects(objects);
        }
        
        for(int i = 0 ; i < 5 ; i++)
            objects.add(new Asteroid(this));
    }
    
    private void createShip(){
        List<Object> objects = this.getObjects();
    
        if(objects == null){
            objects = new LinkedList<>();
        
            this.setObjects(objects);
        }
    
        Ship ship = new Ship(this);

        objects.add(ship);
    }
    
    private void initialize(){
        this.createStars();
        this.createAsteroids();
        this.createShip();
        
        this.addControls();
    }
    
    public void addControls(){
        super.addKeyListener(new ControlListener());
    }
    
    public int getSpeed(){
        return this.speed;
    }
    
    protected void setSpeed(int speed){
        this.speed = speed;
    }
    
    public int getMaxWidth(){
        return this.maxWidth;
    }
    
    protected void setMaxWidth(int maxWidth){
        this.maxWidth = maxWidth;
    }
    
    public int getMaxHeight(){
        return this.maxHeight;
    }
    
    protected void setMaxHeight(int maxHeight){
        this.maxHeight = maxHeight;
    }
    
    public int getMaxStars(){
        return this.maxStars;
    }
    
    protected void setMaxStars(int maxStars){
        this.maxStars = maxStars;
    }
    
    public List<Object> getObjects(){
        return this.objects;
    }
    
    protected void setObjects(List<Object> objects){
        this.objects = objects;
    }
    
    public void animate(){
        try{
            List<Object> objects = this.getObjects();
    
            for(Object object: objects)
                object.animate();
    
            repaint();
        }
        catch(Throwable e){
            e.printStackTrace();
        }
    }
    
    public void checkInterceptions(){
        try{
            List<Object> objects = this.getObjects();
            List<Interceptable> interceptables = objects.parallelStream().
                    filter(Interceptable.class::isInstance).
                    map(Interceptable.class::cast).
                    filter(i -> !i.isDestroyed()).
                    collect(Collectors.toList());
    
            if(interceptables != null && !interceptables.isEmpty()){
                Ship ship = null;
        
                try{
                    ship = interceptables.parallelStream().
                            filter(Ship.class::isInstance).
                            map(Ship.class::cast).
                            findFirst().
                            get();
            
                    List<Asteroid> asteroids = interceptables.parallelStream().
                            filter(Asteroid.class::isInstance).
                            map(Asteroid.class::cast).
                            collect(Collectors.toList());
            
                    if(asteroids != null && !asteroids.isEmpty()){
                        for(Asteroid asteroid: asteroids){
                            if(ship.wasIntercepted(asteroid))
                                break;
    
                            List<Photon> photons = interceptables.parallelStream().
                                    filter(Photon.class::isInstance).
                                    map(Photon.class::cast).
                                    collect(Collectors.toList());
    
                            if(photons != null && !photons.isEmpty()){
                                for(Photon photon: photons){
                                    if(asteroid.wasIntercepted(photon))
                                        break;
                                }
                            }
                        }
                    }
                }
                catch(NoSuchElementException e){
                }
            }
        }
        catch(Throwable e){
            e.printStackTrace();
        }
       
        //interceptables = objects.parallelStream().filter(o -> (o instanceof Interceptable)).collect(Collectors.toList());
        //Ship ship = (Ship)objects.parallelStream().filter(o -> o ins1tanceof Ship && ((Interceptable)o)).findFirst().get();
        
        //if(!ship.isDestroyed()){
        //    List<Object> interceptables = objects.parallelStream().filter(o -> o instanceof Interceptable && !((Interceptable) o).isDestroyed() && !o.equals(ship)).collect(Collectors.toList());
    
        //    if(interceptables != null && !interceptables.isEmpty()){
        //        for(int i = 0; i < interceptables.size(); i++){
        //            Interceptable interceptable = (Interceptable) interceptables.get(i);
    
        //            if(ship.wasIntercepted(interceptable))
        //                break;
        //        }
        //    }
        //}
    }
    
    public void paint(Graphics g){
        this.g = (Graphics2D) g;

        List<Object> objects = this.getObjects();

        for(Object object: objects)
            object.paint();
    }
    
    class ControlListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e){
        }
        
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_ESCAPE)
                System.exit(0);
        }
        
        @Override
        public void keyReleased(KeyEvent e){
        }
    }
}