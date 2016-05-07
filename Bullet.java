package tanks;

import java.util.ConcurrentModificationException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rohan
 */
public final class Bullet implements Runnable {

    public int damage;
    public double x;
    public double y;
    public double speedX;
    public double speedY;
    public AtomicBoolean alive = new AtomicBoolean(true);
    public static final int ACCURACY = 100;
    public static final double GRAVITY = 1;
    public int turns = 0;

    public Bullet(double inX, double inY, double inXSpeed, double inYSpeed, int inDmg) {
        damage = inDmg;

        x = inX;
        y = inY;
        speedX = inXSpeed;
        speedY = inYSpeed;
//        for (int i = 0; i < ACCURACY; i++) {
//            takeStep();
//        }
    }

    void takeStep() {
        try {
            Thread.sleep(0, 100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bullet.class.getName()).log(Level.SEVERE, null, ex);
        }
        x += speedX / ACCURACY;
        speedX += Main.wind / 100 / ACCURACY;
        y -= speedY / ACCURACY;
        speedY -= GRAVITY / ACCURACY;
    }

    /**
     * shoots the bullet
     */
    @Override
    public void run() {

        shooting:
        while (alive.get() && Main.round.get() && (int) x < MyPanel.xSize && (int) x > -5 && (int) y - (MyPanel.ySize - Main.heights[(int) x]) < -5) {
            try {
                turns++;
                takeStep();
                for (Bullet b : Tank.bullets) {
                    if (b != this && b.alive.get()) {
                        if (Math.sqrt(Math.pow(this.x - b.x, 2) + Math.pow(this.y - b.y, 2)) <= 6) {
                            b.alive.set(false);
                            this.alive.set(false);//on collision with another bullet both break;  
                        }
                    }
                }
                if (turns > ACCURACY) {
                    for (int i = 0; alive.get() && i < Main.tanks.length; i++) {

                        int bulletDiffY = (int) Math.abs(this.y - (MyPanel.ySize - Main.heights[Main.tanks[i].x] - 10));
                        int bulletDiffX = (int) Math.abs(this.x - (Main.tanks[i].x - 10));
//System.out.println(bulletDiffX+"|"+bulletDiffY);
                        if (alive.get() && bulletDiffX <= 15 && bulletDiffY <= 25) {

                            if (alive.get() && Main.round.get()) {
                                Main.tanks[i].health -= damage;
                                Main.tanks[(i + 1) % Main.tanks.length].moneys += damage;
                                alive.set(false);
                            }
                        }

                    }
                }
            } catch (ConcurrentModificationException ex) {
                //TODO learn good multithreaded paradigms!
            }
        }

        System.out.println("Hit ground at " + (int) x + "|" + (int) y);
        if (alive.get() && Main.round.get() && x < MyPanel.xSize && x >= 0 && !((int) y - (MyPanel.ySize - Main.heights[(int) x]) > 0)) {
            try {
                for (int i = (int) x - 25; alive.get() && i <= x + 25; i++) {
                    if (i >= 0 && i < MyPanel.xSize) {
                        Main.heights[i] -= Math.sqrt(Math.pow(25, 2) - Math.pow(i - x, 2));
                    }

                    if (i - 1 > 0 && (Main.heights[i] <= 0 && Main.heights[i - 1] >= 0)) {
                        Main.heights[i] = Main.heights[i - 1];
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.err.println("I feel bad for you! Here is a nasty stack trace,"
                  + "Do not worry because this exception is excepted");
                System.out.println("===============================================");
                ex.printStackTrace();
            } catch (Exception ex) {
                System.err.println("You messed up royally and totally, this is unexpected");
                System.out.println("===============================================");
                ex.printStackTrace();
            }
        }
        alive.set(false);
    }
}
