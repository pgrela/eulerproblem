package pgrela.eulerproblem.problem62;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Longs;

public class CubicPermutations implements EulerSolver {

    public static final long LONG = (long) Math.sqrt(Long.MAX_VALUE);

    public static void main(String[] args) {
        printSolution(CubicPermutations.class);
    }

    public long solve() {
        Map<Long, Integer> counter = new HashMap<>();
        long winner = LongStream.range(0, LONG).sequential()
                .map(i -> i * i * i)
                //.filter(i->orderDigitsInNumber(i)==125).peek(System.out::println)
                .map(CubicPermutations::orderDigitsInNumber)
                .peek(i -> counter.put(i, counter.getOrDefault(i, 0) + 1))
                .filter(i -> counter.get(i) == 4)
                .findFirst().getAsLong();

        return LongStream.range(0, LONG)
                .map(i -> i * i * i)
                .filter(i -> orderDigitsInNumber(i) == winner)
                .findFirst().getAsLong();
    }

    public static long orderDigitsInNumber(long i) {
        int[] digits = Longs.toDigitArray(i);
        Arrays.sort(digits);
        for (int j = 0; j < digits.length / 2; j++) {
            int a = digits[j];
            int other = digits.length - 1 - j;
            digits[j] = digits[other];
            digits[other] = a;
        }
        return Longs.fromDigitArray(digits);
    }
}

