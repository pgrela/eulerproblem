package pgrela.eulerproblem.problem103;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SpecialSubsetSumsOptimum implements EulerSolver {

    public static final int SIZE = 7;

    public static void main(String[] args) {
        printSolution(SpecialSubsetSumsOptimum.class);
    }

    public String solveToString() {
        return solveForSize(SIZE);
    }

    public String solveForSize(int setSize) {
        int seed = 20;//performance guess
        int[] currentSet = new int[setSize];
        String solution = null;
        range(0, currentSet.length).forEach(i -> currentSet[i] = i + seed);
        int minimalSumSet = Integer.MAX_VALUE;
        while (minimalPossibleSum(currentSet[0], setSize) < minimalSumSet) {
            if (checkAllSubSums(currentSet) && checkAllHalfSums(currentSet)) {
                int currentSum = of(currentSet).sum();
                if (minimalSumSet > currentSum) {
                    minimalSumSet = currentSum;
                    solution = of(currentSet).mapToObj(String::valueOf).reduce("", (s, t) -> s + t);
                }
            }
            incrementOrderedUniqueSet(currentSet, seed);
        }
        return solution;
    }

    private int minimalPossibleSum(int firstElement, int numberOfElements) {
        return (firstElement * 2 + numberOfElements - 1) * numberOfElements / 2;
    }

    private boolean checkAllHalfSums(int[] array) {
        int[] indices = new int[(array.length - 1) / 2];
        int sum = of(array).sum();
        range(0, indices.length).forEach(i -> indices[i] = i);
        while (true) {
            if (indices[indices.length - 1] >= array.length) {
                return true;
            }
            int a = range(0, indices.length).map(i -> array[indices[i]]).sum();
            if (a >= sum - a) {
                return false;
            }
            incrementOrderedUniqueSet(indices, 0);
        }
    }

    private void incrementOrderedUniqueSet(int[] initial, int resetStart) {
        int elementToBump = 0;
        while (elementToBump < initial.length - 1 && initial[elementToBump] + 1 == initial[elementToBump + 1]) {
            ++elementToBump;
        }
        ++initial[elementToBump];
        for (int i = 0; i < elementToBump; i++) {
            initial[i] = i + resetStart;
        }
    }

    private boolean checkAllSubSums(int[] set) {
        Set<Integer> achievedSums = new HashSet<>();
        return range(0, 1 << set.length).sequential().map(combinationDescriptor ->
                range(0, set.length)
                        .filter(maybeIncludedInSum -> (combinationDescriptor & (1 << maybeIncludedInSum)) != 0)
                        .map(i -> set[i])
                        .sum()
        ).allMatch(achievedSums::add);
        //this is much faster !!!
//        for (int combinationDescriptor = 0; combinationDescriptor < 1 << set.length; combinationDescriptor++) {
//            int s = 0;
//            for (int maybeIncludedInSum = 0; maybeIncludedInSum < set.length; maybeIncludedInSum++) {
//                if ((combinationDescriptor & (1 << maybeIncludedInSum)) != 0) {
//                    s += set[maybeIncludedInSum];
//                }
//            }
//            if (achievedSums.contains(s)) {
//                return false;
//            }
//            achievedSums.add(s);
//        }
//        return true;
    }

}
