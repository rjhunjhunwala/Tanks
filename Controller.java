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
public class Controller implements KeyListener {

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
            case KeyEvent.VK_UP:
                Main.tanks[1].gunAngle -= Math.PI / 90;
                break;
            case KeyEvent.VK_DOWN:
                Main.tanks[1].gunAngle += Math.PI / 90;

                break;
            case KeyEvent.VK_LEFT:
                Main.tanks[1].move(-8);
                break;
            case KeyEvent.VK_RIGHT:
                Main.tanks[1].move(8);
                break;

        }
        gui.f.repaint();
    }
    public static int turn = 0;

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_SPACE:
                Main.tanks[1].shoot();
               // turn++;
             
                turn %= Main.tanks.length;
                break;
        }
    }

}
