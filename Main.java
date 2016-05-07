/*In place of a copyright notice the author has left a blessing, please see any
* relevant documents for qualifying information
* "Know your enemy as you know yourself" - Sun Tzu "Art of War"
 */
package tanks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JOptionPane;

/**
 * Sincerest apologies for the excessive use of try catches in this code
 * @author rohan
 */
public class Main {
public static double wind=0;
    public static Tank[] tanks;
    public static int[] heights = new int[MyPanel.xSize];
   public static AtomicBoolean round=new AtomicBoolean(true);
    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        tanks = makeTanks(new Tank(30), new Tank(MyPanel.xSize - 30));
        gui.doGui();
        loadMap();
        StateHandler Jackson =new StateHandler(); //election of 1828
        Thread th=new Thread(Jackson);
        th.start();//enable or disable wind by (un)commenting this line
        tanks[1].gunAngle+=Math.PI;
        Thread.sleep(170);
        while (true) {
        
            for (Tank t : tanks) {
                if (heights[(int) t.x] <= 0) {
                    //t.health = -10;
                }
                if (t.health <= 0) {
                    //wind=0;
                    round.set(false);
                    Tank.bullets=new ArrayList(0);
                    doEndOfRoundShop();                    
                    Tank.bullets=new ArrayList(0);
                    tanks[1].gunAngle+=Math.PI;
                round.set(true);
                }
                
            }
            gui.f.repaint();
        }
    }

    public static Tank[] makeTanks(Tank... t) {
        return t;
    }
   

    public synchronized static void loadMap(){
        Generator.genMap();
        heights = Generator.heights.clone();

    }

   

    private synchronized static void doEndOfRoundShop() {
wind=(int) ((Math.random()*9)-4);
        for (int i = 0; i < tanks.length; i++) {
            String[] options = getStringArray("No Thanks!,1,0", 
              "Health,10," + (int) Math.pow(tanks[i].MAX_HEALTH / 10, 2),"Damage,10,"+(int) Math.pow(tanks[i].dmg / 2, 2));
            String input = (String) JOptionPane.showInputDialog(null, "Upgrades for Player: " + (i + 1) + "|" + tanks[i].moneys,
              "Ugrade merchant", JOptionPane.QUESTION_MESSAGE, null, // Use
              // default
              // icon
              options, // Array of choices
              "None"); // Initial choice
            System.out.println(input);
            if (input == null||input.equals("None")||input.toString().equals("null")) {
                continue;
            }

            String[] parsedInput = input.split(",");
            System.out.println(Arrays.toString(parsedInput));
            int cost = Integer.parseInt(parsedInput[2]);
            int amount = Integer.parseInt(parsedInput[1]);
            if (tanks[i].moneys >= cost) {
     tanks[i].moneys-=cost;
                switch (parsedInput[0]) {
                    case "Health":
                        tanks[i].MAX_HEALTH+=amount;
                        break;
                    case "Fuel":
                        tanks[i].MAX_FUEL+=amount;
                        break;
                              case "Damage":
                        tanks[i].dmg+=amount;
                        break;
                }
            }
            tanks = makeTanks(new Tank(30, tanks[0].moneys, tanks[0].MAX_HEALTH, tanks[0].MAX_FUEL,tanks[0].dmg), new Tank(MyPanel.xSize - 30, tanks[1].moneys, tanks[1].MAX_HEALTH, tanks[1].MAX_FUEL,tanks[1].dmg));
            loadMap();
        }
    }
    

    public static String[] getStringArray(String... array) {
        return array;
    }
}
