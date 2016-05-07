/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author rohan
 */
class MyPanel extends JPanel {
public static final int xSize=1024;
public static final int ySize=480;
public static final Dimension DIMENSIONS=new Dimension(xSize,ySize);
private static final long serialVersionUID = 1L;

	public MyPanel() {
          
		setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.blue);
	}

	@Override
	public Dimension getPreferredSize() {
		return DIMENSIONS;
	}

	@Override
	public void paintComponent(Graphics g) {
try{
            g.setColor(Color.white);
            Tank p1=Main.tanks[0];
g.drawString("Player "+1+"|Health: "+p1.health+"|Angle: "
  +Math.round(Math.toDegrees(p1.gunAngle))+"|"+"Money: "+p1.moneys+"|"+"|Wind: "+(int) Main.wind+"|", 5, 10);
p1=Main.tanks[1];
g.drawString("Player "+2+"|Health: "+p1.health+"|Angle: "
  +Math.round(Math.toDegrees(p1.gunAngle))+"|"+"Money: "+p1.moneys+"|"+"|Wind: "+(int) Main.wind+"|", xSize-295, 10);      
g.setColor(Color.orange);
            for(int i=0;i<xSize;i++){
        g.drawLine(i,ySize-Main.heights[i], i, ySize);
            }
        for(Tank t:Main.tanks){
          g.setColor(Color.gray);  
        g.fillRect(t.x-20, ySize-Main.heights[t.x]-20, 20, 20);
        g.setColor(Color.black);
        if(t.bullets!=null)
            for(Bullet b: t.bullets){
                if(b.alive.get())
    g.fillOval((int) b.x-5, (int) b.y-5, 10, 10);
}
g.drawLine(t.x-10,ySize- Main.heights[t.x]-10,(int) (t.x-10+Math.cos(t.gunAngle)*15), (int) (ySize-Main.heights[t.x]-10-Math.sin(t.gunAngle)*15));
        }
}catch(NullPointerException|ConcurrentModificationException ex){
    //who cares!
}    
}
	
}
