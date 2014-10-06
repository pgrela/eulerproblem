package pgrela.eulerproblem.problem53;

import static java.util.stream.IntStream.iterate;
import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.stream.IntStream;

import pgrela.eulerproblem.common.EulerSolver;

public class CombinatoricSelections implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CombinatoricSelections.class);
    }

    public long solve() {
        return iterate(1, i -> i + 1).filter(k -> rangeClosed(2, 6).allMatch(i -> arePermutations(k, k * i))).findFirst().getAsInt();
    }

    private boolean arePermutations(int a, int b) {
        int[] digits = new int[10];
        while(a>0){++digits[a%10];a/=10;}
        while(b>0){--digits[b%10];b/=10;}
        return IntStream.of(digits).allMatch(i->i==0);
    }
}
