package pgrela.eulerproblem.problem88;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.Arrays;

import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.Primes.allDivisors;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ProductSumNumbers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(ProductSumNumbers.class);
    }

    private int[] productSumNumbers = new int[CALCULATE_UP_TO];

    public static final int CALCULATE_UP_TO = 13000;
    public static final int SUM_UP_TO = 12000;

    public long solve() {
        Arrays.fill(productSumNumbers, Integer.MAX_VALUE);
        rangeClosed(2, CALCULATE_UP_TO).forEach(N -> produceSequence(N, 0, N, 0, N));
        return of(productSumNumbers).skip(2).limit(SUM_UP_TO - 1).distinct().sum();
    }

    private void produceSequence(int leftProduct, int sum, int original, int multipliers, int lastMultiplier) {
        if (leftProduct == 1) {
            int length = original - sum + multipliers;
            if (productSumNumbers[length] > original) {
                productSumNumbers[length] = original;
            }
            return;
        }
        allDivisors(leftProduct)
                .filter(i -> i <=lastMultiplier)
                .filter(i -> i !=1)
                .forEach(factor -> produceSequence(leftProduct / factor, sum + factor, original, multipliers + 1, factor));
    }
}
