/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author rohan
 */
public class gui {

    public static JFrame f;
    public static MyPanel p;
    public static final int renderDist = 11;

    public static void doGui() throws InterruptedException {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        f = new JFrame("Tanks");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new MyPanel();
        f.add(p);
        f.pack();
        f.setVisible(true);
        f.addKeyListener(new Controller());
        f.addKeyListener(new Controller2());
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //do window closing code code here
            }
        };
        f.addWindowListener(exitListener);
    f.setBackground(new Color(255,17,17));
    }
}
