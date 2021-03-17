package br.com.concepting.space.core;

import br.com.concepting.space.helpers.Space;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game{
    public static void main(String[] args) throws Throwable{
        int maxWidth = 1024;
        int maxHeight = 768;
        int maxStars = 100;
        int speed = 1;
        Space space = new Space(maxWidth, maxHeight, maxStars, speed);
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Space");
            
            frame.setSize(maxWidth, maxHeight + 30);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.add(space);
        });
    
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        
        service.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run(){
                space.animate();
            }
        }, 0, 10, TimeUnit.MILLISECONDS);
        service.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run(){
                space.checkInterceptions();
            }
        }, 0, 10, TimeUnit.MILLISECONDS);
    }
}
