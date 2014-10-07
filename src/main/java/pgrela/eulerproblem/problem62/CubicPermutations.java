package pgrela.eulerproblem.problem62;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

import pgrela.eulerproblem.common.EulerSolver;

public class CubicPermutations implements EulerSolver {

    public static final long LONG = (long) Math.sqrt(Long.MAX_VALUE);

    public static void main(String[] args) {
        printSolution(CubicPermutations.class);
    }

    public String solveToString() {
        Map<ComparableCharArray, Integer> counter = new HashMap<>();
        ComparableCharArray winner = LongStream.range(0, LONG).sequential()
                .mapToObj(i -> BigDecimal.valueOf(i).pow(3))
                .map(ComparableCharArray::new)
                .peek(i -> counter.put(i, counter.getOrDefault(i, 0) + 1))
                .filter(i -> counter.get(i) == 5)
                .findFirst().get();

        return LongStream.range(0, LONG)
                .mapToObj(i -> BigDecimal.valueOf(i).pow(3))
                .filter(i -> new ComparableCharArray(i).equals(winner))
                .findFirst().get().toString();
    }
    public static class ComparableCharArray{

        private int[] ints;

        public BigDecimal getB() {
            return b;
        }

        private BigDecimal b;

        public ComparableCharArray(BigDecimal b) {
            this.b = b;
            this.ints = b.toString().chars().sorted().toArray();
            for (int i = 0; i < ints.length/2; i++) {
                int k=ints.length-1-i;
                int o=ints[i];
                ints[i]=ints[k];
                ints[k]=o;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ComparableCharArray)) return false;

            ComparableCharArray that = (ComparableCharArray) o;

            return Arrays.equals(ints, that.ints);

        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(ints);
        }

        @Override
        public String toString() {
            return b.toString();
        }
    }
}

