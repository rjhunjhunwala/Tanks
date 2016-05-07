package tanks;
/**
 * used to generate perlin noise
 * @author Rohans
 */
public class Generator{
public static int[] heights=new int[MyPanel.xSize];
  public static final int MAX_HEIGHT=MyPanel.ySize/2;
  /**
   * Generates a smooth map which integer representing the height 
   */
public static void genMap(){
    for(int i=0;i<MyPanel.xSize;i++){
        heights[i]=310;//ensuring that the ints are all starting at 100
    }
//now we will use the midpoint displacement algorithm in the hopes of generating 
//mediocore terrain
midpointDisplace(0,1023,370);
}
 public static void midpointDisplace(int lower, int upper, int VARIANCE) {
        if (Math.abs(lower - upper) <= 1.1) {
            return;
        }
        int middle = (lower + upper) / 2;
            heights[middle] = (int) ((heights[lower] + heights[upper]) / 2 + (Math.random() * VARIANCE/2 - (VARIANCE)));
        
        VARIANCE*=.375;
        midpointDisplace(lower, middle,VARIANCE);
        midpointDisplace(middle, upper,VARIANCE);
    }
}
