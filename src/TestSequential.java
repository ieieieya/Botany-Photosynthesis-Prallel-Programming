package botany;

/**
 *
 * @author dancan
 */
import java.util.*;
import java.io.*;

public class TestSequential {

    private static long startTime = 0;
    /**
     * When called the timing of the program running starts.
     */
    private static void tick() {
        startTime = System.currentTimeMillis();
    }
    /**
     * 
     * @return the time taken
     */
    private static float tock() {
        return (System.currentTimeMillis() - startTime) / 1000.0f;
    }
    /**
     * The args[0] is the name of the input file
     * The args[1] is the name of the output file
     * @param args 
     */
    public static void main(String[] args) {

        try {

            String inFile = args[0]; 
            String outFile = args[1];
            Scanner scanner = new Scanner(new File(inFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFile));

            int NUMBER_OF_TREES = 0;
            double TOTAL_TERRAIN_SUNLIGHT_COVER = 0.0;

            ArrayList<Double> SUNLIGHT_COVER_PER_TREE = new ArrayList<>();

            while (scanner.hasNextLine()) {
                int TERRAIN_WIDTH_X = scanner.nextInt();
                int TERRAIN_LENGTH_Y = scanner.nextInt();

                double[][] array = new double[TERRAIN_WIDTH_X][TERRAIN_LENGTH_Y];

                for (int b = 0; b < TERRAIN_WIDTH_X; b++) {
                    for (int b1 = 0; b1 < TERRAIN_LENGTH_Y; b1++) {
                        double f = scanner.nextDouble();
                        array[b][b1] = f;
                    }
                }
                NUMBER_OF_TREES = scanner.nextInt();
                System.gc();
               
                
                tick();
                for (int i = 0; i < NUMBER_OF_TREES; i++) {
                    int TREE_X = scanner.nextInt();
                    int TREE_Y = scanner.nextInt();
                    int TREE_CANOPY = scanner.nextInt();
                    double SUM_SUNLIGHT_COVER = 0.0;

                    for (int x = TREE_X; x < TREE_CANOPY + TREE_X; x++) {
                        for (int y = TREE_Y; y < TREE_CANOPY + TREE_Y; y++) {
                            if (y < TERRAIN_LENGTH_Y && x < TERRAIN_WIDTH_X) {
                                SUM_SUNLIGHT_COVER += array[x][y];
                            }
                        }
                    }
                    SUNLIGHT_COVER_PER_TREE.add(SUM_SUNLIGHT_COVER);
                    TOTAL_TERRAIN_SUNLIGHT_COVER += SUM_SUNLIGHT_COVER;
                }

                System.out.println(tock());
                break;
            }
            scanner.close();
            //Calculating for average sunlight cover
            bufferedWriter.write(Double.toString(TOTAL_TERRAIN_SUNLIGHT_COVER / NUMBER_OF_TREES) + "\n");
            bufferedWriter.write(String.valueOf(NUMBER_OF_TREES) + "\n");
            for (double n : SUNLIGHT_COVER_PER_TREE) {
                bufferedWriter.write(n + "\n");
            }

        } catch (Exception e) {
        }
        System.exit(0);
    }

}

