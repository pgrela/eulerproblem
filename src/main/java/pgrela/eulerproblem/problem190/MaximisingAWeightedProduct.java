package pgrela.eulerproblem.problem190;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class MaximisingAWeightedProduct implements EulerSolver {

    public static final int RESOLUTION_STEP = 10;

    public static void main(String[] args) {
        printSolution(MaximisingAWeightedProduct.class);
    }

    @Override
    public long solve() {
        long sum = 0;
        for (int m = 2; m <= 15; m++) {
            int resolution = RESOLUTION_STEP;
            int[] x = IntStream.range(0, m).map(j -> RESOLUTION_STEP).toArray();
            double epsilon = 1. / resolution;
            boolean steppedWithScale = true;
            while (steppedWithScale && resolution < ONE_MILLION) {
                steppedWithScale = false;
                boolean stepped = true;
                while (stepped) {
                    stepped = false;
                    outer:
                    for (int left = 0; left < x.length; left++) {
                        for (int right = x.length - 1; left < right; right--) {
                            if (Math.pow(x[left] * epsilon, left + 1) * Math.pow(x[right] * epsilon, right + 1) < Math.pow((x[left] - 1) * epsilon, left + 1) * Math.pow((x[right] + 1) * epsilon, right + 1)) {
                                --x[left];
                                ++x[right];
                                steppedWithScale = stepped = true;
                                break outer;
                            }
                            if (Math.pow(x[left] * epsilon, left + 1) * Math.pow(x[right] * epsilon, right + 1) < Math.pow((x[left] + 1) * epsilon, left + 1) * Math.pow((x[right] - 1) * epsilon, right + 1)) {
                                ++x[left];
                                --x[right];
                                steppedWithScale = stepped = true;
                                break outer;
                            }
                        }
                    }
                }
                for (int j = 0; j < x.length; j++) {
                    x[j] *= RESOLUTION_STEP;
                }
                epsilon /= RESOLUTION_STEP;
                resolution *= RESOLUTION_STEP;
            }
            double finalEpsilon = epsilon;
            sum += IntStream.range(0, x.length).mapToDouble(j -> Math.pow(x[j] * finalEpsilon, j + 1)).reduce(1, (a, b) -> a * b);
        }
        return sum;
    }

}

