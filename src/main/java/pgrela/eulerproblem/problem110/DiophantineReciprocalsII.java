package pgrela.eulerproblem.problem110;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class DiophantineReciprocalsII implements EulerSolver {

    public static final int[][] MIN_MAX_PATTERN = new int[][]{
            {2, 6},
            {1, 4},
            {1, 3},
            {1, 3},
            {1, 3},
            {1, 2},
            {1, 2},
            {1, 1},
            {1, 1},
            {0, 1},
            {0, 1},
            {0, 1},
            {0, 1}
    };

    public static void main(String[] args) {
        printSolution(DiophantineReciprocalsII.class);
    }


    public String solveToString() {
        int[] descriptor = new int[MIN_MAX_PATTERN.length];
        IntStream.range(0, descriptor.length).forEach(i -> descriptor[i] = MIN_MAX_PATTERN[i][0]);
        Map<BigInteger, Long> candidates = new HashMap<>();
        do {
            candidates.put(factorDescriptorToNumber(descriptor), solutionsFromDescriptor(descriptor));
        } while (incrementWithinMinMax(descriptor, MIN_MAX_PATTERN));
        return candidates.entrySet()
                .stream()
                .filter(e -> e.getValue() > 4 * ONE_MILLION)
                .map(Map.Entry::getKey)
                .reduce((a, b) -> a.compareTo(b) < 0 ? a : b)
                .get()
                .toString();
    }

    private boolean incrementWithinMinMax(int[] current, int[][] counter) {
        int elementToIncrement = 0;
        while (elementToIncrement < current.length && current[elementToIncrement] + 1 > counter[elementToIncrement][1]) {
            ++elementToIncrement;
        }
        if (elementToIncrement == current.length) {
            return false;
        }
        ++current[elementToIncrement];
        for (int i = 0; i < elementToIncrement; i++) {
            current[i] = counter[i][0];
        }
        return true;
    }

    private long solutionsFromDescriptor(int[] descriptor) {
        return IntStream.of(descriptor).map(i -> 2 * i + 1).reduce(1, (a, b) -> a * b) / 2 + 1;
    }

    BigInteger factorDescriptorToNumber(int[] descriptor) {
        int[] primesToFactorizeUpTo100000 = Primes.getPrimesToFactorizeUpTo100000();
        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < descriptor.length; i++) {
            result = result.multiply(BigInteger.valueOf(Maths.pow(primesToFactorizeUpTo100000[i], descriptor[i])));
        }
        return result;
    }


}
