/**
 * This is used to listen to the character moves typed in by the user
 */
package tanks;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author rohan
 */
public class Controller2 implements KeyListener {

    @Override

    public void keyTyped(KeyEvent e) {

    }

    @Override
    /**
     * @param KeyEvent the event when a key gets pressed moves the main
     * character using w a s and d keys space to drop a grenade q to shoot
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                Main.tanks[turn].gunAngle += Math.PI / 90;
                break;
            case KeyEvent.VK_S:
                Main.tanks[turn].gunAngle -= Math.PI / 90;

                break;
            case KeyEvent.VK_A:
                Main.tanks[turn].move(-8);
                break;
            case KeyEvent.VK_D:
                Main.tanks[turn].move(8);
                break;
        }
        gui.f.repaint();
    }
    public static int turn = 0;

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q:
                Main.tanks[0].shoot();
               // turn++;
             
                turn %= Main.tanks.length;
                break;
        }
    }

}
