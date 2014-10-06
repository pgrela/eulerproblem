package pgrela.eulerproblem.problem4;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.util.stream.IntStream;

import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class LargestPalindromeProduct implements EulerSolver {
    public static void main(String[] args) {
        printSolution(LargestPalindromeProduct.class);
    }

    public long solve() {
        return rangeClosed(100, 999)
                .mapToObj(n -> rangeClosed(100, n).map(k -> k * n))
                .reduce(IntStream::concat)
                .get()
                .filter(Maths::isPalindrome)
                .max()
                .getAsInt();
    }

}
