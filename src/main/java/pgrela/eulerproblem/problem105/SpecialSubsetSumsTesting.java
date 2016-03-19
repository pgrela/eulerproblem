package pgrela.eulerproblem.problem105;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.Files.getLinesStream;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SpecialSubsetSumsTesting implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem105/sets.txt";
    public static final int MIN = 0;
    public static final int MAX = 1;

    public static void main(String[] args) {
        printSolution(SpecialSubsetSumsTesting.class);
    }

    public long solve() {
        return getLinesStream(RESOURCE_FILE)
                .map((String s) -> Stream.of(s.split(",")).mapToInt(Integer::valueOf).toArray())
                .filter(this::checkAllSubSums)
                .mapToInt(a -> IntStream.of(a).sum())
                .sum();
    }

    public boolean checkAllSubSums(final int[] set) {
        Set<Integer> achievedSums = new HashSet<>();
        int[][] minmax = new int[set.length + 1][2];
        range(0, minmax.length).forEach(i -> {
            minmax[i][MIN] = Integer.MAX_VALUE;
            minmax[i][MAX] = Integer.MIN_VALUE;
        });
        for (int combinationDescriptor = 1; combinationDescriptor < (1 << set.length) - 1; combinationDescriptor++) {
            int currentSum = 0;
            int elements = 0;
            for (int maybeIncludedInSum = 0; maybeIncludedInSum < set.length; maybeIncludedInSum++) {
                if ((combinationDescriptor & (1 << maybeIncludedInSum)) != 0) {
                    currentSum += set[maybeIncludedInSum];
                    ++elements;
                }
            }

            if (currentSum < minmax[elements - 1][MAX] || currentSum > minmax[elements + 1][MIN]) {
                return false;
            }
            minmax[elements][MIN] = Math.min(minmax[elements][MIN], currentSum);
            minmax[elements][MAX] = Math.max(minmax[elements][MAX], currentSum);

            if (!achievedSums.add(currentSum)) {
                return false;
            }
        }
        return true;
    }

}
