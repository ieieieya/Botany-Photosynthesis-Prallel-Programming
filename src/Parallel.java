//package botany;

/**
 *
 * @author dancan
 */
import java.util.concurrent.RecursiveTask;
import java.util.*;

public class Parallel extends RecursiveTask<ArrayList<Double>> {

    private final Node[] TREE_VALUES;
    private final Double[][] SUNLIGHT_VALUES;
    private final int hi;
    private final int lo;
    static final int SEQUENTIAL_CUTOFF = 550000;

    ArrayList<Double> treeExposure = new ArrayList<>();

    Parallel(Double[][] SUNLIGHT_VALUES, Node[] TREE_VALUES, int hi, int lo) {
        this.SUNLIGHT_VALUES = SUNLIGHT_VALUES;
        this.TREE_VALUES = TREE_VALUES;
        this.hi = hi;
        this.lo = lo;
    }

    @Override
    protected ArrayList<Double> compute() {// return answer - instead of run  

        if ((hi - lo) < SEQUENTIAL_CUTOFF) {
            for (int i = lo; i < hi; i++) {
                double sum = 0.0;
                Node tree = TREE_VALUES[i];
                int X_VALUE = tree.TREE_X;
                int Y_VALUE = tree.TREE_Y;
                int TREE_CANOPY = tree.TREE_CANOPY;
                for (int d = X_VALUE; d < TREE_CANOPY + X_VALUE; d++) {
                    for (int n = Y_VALUE; n < TREE_CANOPY + Y_VALUE; n++) {
                        if (n < SUNLIGHT_VALUES.length && d < SUNLIGHT_VALUES.length) {
                            sum += SUNLIGHT_VALUES[d][n];
                        }
                    }
                }
                treeExposure.add(sum);
            }
            return treeExposure;
        } else {
            Parallel left = new Parallel(SUNLIGHT_VALUES, TREE_VALUES, (hi + lo) / 2, lo);
            Parallel right = new Parallel(SUNLIGHT_VALUES, TREE_VALUES, hi, (hi + lo) / 2);
            left.fork();
            ArrayList<Double> rightAns = right.compute();
            ArrayList<Double> leftAns = left.join();
            List<Double> newSunlight = new ArrayList<>(leftAns);
            newSunlight.addAll(rightAns);

            return new ArrayList<>(newSunlight);
        }
    }

}
