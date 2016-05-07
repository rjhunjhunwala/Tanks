package tanks;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rohans
 */
public class Tank {
    public int x;
    int power=30;
    public int health;
    int fuel;
    int MAX_FUEL;
    int MAX_HEALTH;
    int dmg;
    int moneys;
    public static ArrayList<Bullet> bullets=new ArrayList(0);
    public double gunAngle=0.0;
    public Tank(int inX){
        x=inX;
    health=100;
    MAX_HEALTH=100;
    MAX_FUEL=25;
    fuel=MAX_FUEL;
    dmg=20;
    }
    public Tank(int inX,int inMoney, int inMaxHealth,int inMaxFuel,int inDMG){
        dmg=inDMG;
        x=inX;
        moneys=inMoney;
        MAX_HEALTH=inMaxHealth;
        MAX_FUEL=inMaxFuel;
        fuel=MAX_FUEL;
     health=MAX_HEALTH;   
    }
    public void move(int moveX){
if(x+moveX<0||x+moveX>MyPanel.xSize){
    return;
}
        if(fuel-1>=0){
            x+=moveX;
            ;
        }

    }
    public void shoot(){

    bullets.add(new Bullet((int) (x+(Math.cos(gunAngle)*20)),MyPanel.ySize-Main.heights[x]-20,Math.cos(gunAngle)*30,(Math.sin(gunAngle)*30),dmg));
    Thread t=new Thread(bullets.get(bullets.size()-1));
    t.start();
    }
}
