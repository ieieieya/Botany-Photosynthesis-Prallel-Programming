//package botany;

/**
 *
 * @author dancan
 */
import java.util.concurrent.RecursiveTask;
import java.util.*;

public class SumArray extends RecursiveTask<Double> {

    int lo; // arguments
    int hi;
    ArrayList<Double> arr;
    static final int SEQUENTIAL_CUTOFF = 500;

    Double ans = 0.0; // result 

    SumArray(ArrayList<Double> a, int l, int h) {
        lo = l;
        hi = h;
        arr = a;
    }

    @Override
    protected Double compute() {// return answer - instead of run
        if ((hi - lo) < SEQUENTIAL_CUTOFF) {
            double ansa = 0.0;
            for (int i = lo; i < hi; i++) {
                ansa += arr.get(i);
            }
            return ansa;
        } else {
            SumArray left = new SumArray(arr, lo, (hi + lo) / 2);
            SumArray right = new SumArray(arr, (hi + lo) / 2, hi);
            left.fork();
            double rightAns = right.compute();
            double leftAns = left.join();
            return leftAns + rightAns;
        }
    }

}
