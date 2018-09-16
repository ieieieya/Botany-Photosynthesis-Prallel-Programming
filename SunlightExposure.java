package botany;

/**
 *
 * @author dancan
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SunlightExposure {

    private static int TERRAIN_WIDTH_X;
    private static int TERRAIN_LENGTH_Y;
    private static float TOTAL_AMOUNT_SHONE = 0;
    private static final ArrayList<Float> TREE_SUNLIGHT = new ArrayList<>();
    private static int NUMBER_OF_TREES;
    private static final ArrayList<ArrayList<Float>> SUNLIGHT = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 
        try{
         input = new Scanner(new File("input.txt"));
        }catch(FileNotFoundException e){}
        
        while (input.hasNextLine()) {
            String TERRAIN_VALUES[] = input.nextLine().split(" ");
            TERRAIN_WIDTH_X = Integer.parseInt(TERRAIN_VALUES[0]);
            TERRAIN_LENGTH_Y = Integer.parseInt(TERRAIN_VALUES[1]);
            int SUNLIGHT_HOURS_LENGTH = TERRAIN_WIDTH_X * TERRAIN_LENGTH_Y;
            String SUNLIGHT_HOURS[] = (input.nextLine().split(" "));

            if (SUNLIGHT_HOURS.length != SUNLIGHT_HOURS_LENGTH) {
                System.out.println("Some values are missing");
                System.exit(0);
            }

            int d = 0;

            for (int j = 0; j < TERRAIN_LENGTH_Y; j++) {
                for (int k = 0; k < TERRAIN_WIDTH_X; k++) {
                    float value = Float.parseFloat(SUNLIGHT_HOURS[d]);
                    SUNLIGHT.add(new ArrayList<>());
                    SUNLIGHT.get(j).add(value);
                    d += 1;
                }
            }
            NUMBER_OF_TREES = Integer.parseInt(input.nextLine());
            for (int n = 0; n < NUMBER_OF_TREES; n++) {
                float sum = 0;
                String arr[] = input.nextLine().split(" ");
                int Tree_X_Corner = Integer.parseInt(arr[0]);
                int Tree_Y_Corner = Integer.parseInt(arr[1]);
                int Canopy = Integer.parseInt(arr[2]);
                for (int r = Tree_X_Corner; r < Tree_X_Corner + Canopy; r++) {
                    try {
                        for (int s = Tree_Y_Corner; s < Tree_Y_Corner + Canopy; s++) {
                            float value = SUNLIGHT.get(r).get(s);
                            sum += value;
                            //System.out.println(r+" "+s+" "+value);
                        }
                    } catch (IndexOutOfBoundsException e) {

                    }

                }
                TREE_SUNLIGHT.add(sum);
                TOTAL_AMOUNT_SHONE += sum;

            }
            System.out.println(TOTAL_AMOUNT_SHONE / NUMBER_OF_TREES);
            System.out.println(NUMBER_OF_TREES);
            for (int a = 0; a < TREE_SUNLIGHT.size(); a++) {
                System.out.println(TREE_SUNLIGHT.get(a));
            }
            SUNLIGHT.clear();
            TREE_SUNLIGHT.clear();
            TOTAL_AMOUNT_SHONE = 0;
        }

    }

}
