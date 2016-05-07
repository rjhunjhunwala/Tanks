package tanks;


import java.util.logging.Level;
import java.util.logging.Logger;
import tanks.Main;
import tanks.Tank;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Essentially the Andrew Jackson of this program
 * @author Rohans
 */
public class StateHandler implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(StateHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){
  if(Math.abs(Main.wind)>40){
      Main.wind=Math.random()*9-4;
  }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(StateHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
Main.wind+=Math.random()>.5?-5*Math.random():5*Math.random();
        if(Math.random()<.275){
           double gust=Math.random()>.5?-21-Math.random()*10:21+Math.random()*10;
            Main.wind+=gust;
                try {
                    Thread.sleep((long) (3600+Math.random()*8000));
                } catch (InterruptedException ex) {
                    Logger.getLogger(StateHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
              Main.wind-=gust;  
        }
           // gui.f.repaint();
        }
    }
    
}
