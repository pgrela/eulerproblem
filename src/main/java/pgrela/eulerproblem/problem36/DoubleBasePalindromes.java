package pgrela.eulerproblem.problem36;

import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.Maths.isPalindrome;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

public class DoubleBasePalindromes implements EulerSolver {
    public static void main(String[] args) {
        printSolution(DoubleBasePalindromes.class);
    }

    public long solve() {
        return range(1, ONE_MILLION).filter(Maths::isPalindrome).filter(n-> isPalindrome(n, 2)).sum();
    }

}
