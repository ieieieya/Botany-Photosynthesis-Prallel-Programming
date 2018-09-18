//package botany;

/**
 *
 * @author dancan
 */
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.io.*;

public class TestParallel {

    private static long startTime = 0;

    private static void tick() {
        startTime = System.currentTimeMillis();
    }

    private static float tock() {
        return (System.currentTimeMillis() - startTime) / 1000.0f;
    }
    static final ForkJoinPool FJPOOL01 = new ForkJoinPool();
    static final ForkJoinPool FJPOOL02 = new ForkJoinPool();

    static ArrayList<Double> arrayOfTreeExposureSums(Double[][] SUNLIGHT_VALUES, Node[] TREE_VALUES) {
        return FJPOOL01.invoke(new Parallel(SUNLIGHT_VALUES, TREE_VALUES, TREE_VALUES.length, 0));
    }

    static Double averageExposure(ArrayList<Double> arrat) {
        return FJPOOL02.invoke(new SumArray(arrat, 0, arrat.size()));
    }

    public static void main(String[] args) {
        try {
            String inFile = args[0], outFile = args[1];
            Scanner scanner = new Scanner(new File(inFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFile));
            int numberOfTrees = 0;
            Node[] TREE_VALUES = null;
            Double[][] SUNLIGHT_VALUES = null;
            
            //ArrayList<Double> sumOfTrees = new ArrayList<>();
            while (scanner.hasNextLine()) {
                int TERRAIN_WIDTH_X = scanner.nextInt();
                int TERRAIN_LENGTH_Y = scanner.nextInt();

                SUNLIGHT_VALUES = new Double[TERRAIN_WIDTH_X][TERRAIN_LENGTH_Y];
                //Reading in all the values for the matrix
                for (int x = 0; x < TERRAIN_WIDTH_X; x++) {
                    for (int y = 0; y < TERRAIN_WIDTH_X; y++) {
                        double f = scanner.nextDouble();
                        SUNLIGHT_VALUES[x][y] = f;
                    }

                }
                numberOfTrees = scanner.nextInt();
                TREE_VALUES = new Node[numberOfTrees];

                for (int i = 0; i < numberOfTrees; i++) {
                    
                    int TREE_X = scanner.nextInt();
                    int TREE_Y = scanner.nextInt();
                    int TREE_CANOPY = scanner.nextInt();
                    
                    Node tree = new Node(TREE_X, TREE_Y, TREE_CANOPY);
                    TREE_VALUES[i] = tree;

                }
                scanner.close();
                break;
            }
            ArrayList<Float> timeArray = new ArrayList<>();
            ArrayList<Double> testing = new ArrayList<>();
            //Running the rogrma 5 times.
            for(int r = 0 ; r<10 ;r++)
            {
                if(r!=9){
                    tick();
                    testing = arrayOfTreeExposureSums(SUNLIGHT_VALUES, TREE_VALUES);
                    float time = tock();
                    System.out.println("Runtime"+r+" takes: " + time + " seconds");
                    timeArray.add(time);
                    testing.clear();
                }
                else{
                    tick();
                    testing = arrayOfTreeExposureSums(SUNLIGHT_VALUES, TREE_VALUES);
                    float time = tock();
                    System.out.println("Runtime"+r+" takes: " + time + " seconds");
                    timeArray.add(time);
                }  
            }
            float TOTAL_ALL_RUNTIMES = 0;
            TOTAL_ALL_RUNTIMES = timeArray.stream().map((x) -> x).reduce(TOTAL_ALL_RUNTIMES, (accumulator, _item) -> accumulator + _item);
            System.out.println("Average of all runtimes is: " +TOTAL_ALL_RUNTIMES/10);
            
            double SUM_ALL_SUNLIGHT_COVER = 0.0;
            for (double y : testing) {
                SUM_ALL_SUNLIGHT_COVER += y;
            }
            bufferedWriter.write(Double.toString(SUM_ALL_SUNLIGHT_COVER / numberOfTrees) + "\n");
            bufferedWriter.write(String.valueOf(numberOfTrees) + "\n");
            for (double y : testing) {
                bufferedWriter.write(y + "\n");
            }
            bufferedWriter.close();
        } catch (Exception e){}
        System.exit(0);
    }

}
