package pgrela.eulerproblem.problem55;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import pgrela.eulerproblem.common.EulerSolver;

public class LychrelNumbers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(LychrelNumbers.class);
    }

    public long solve() {
        return IntStream.range(0, 10000).filter(this::isNotPalindromicWithin50Iterations).count();
    }

    private boolean isNotPalindromicWithin50Iterations(long n) {
        BigDecimal b = new BigDecimal(n);
        for (int i = 0; i < 50; i++) {
            if (isPalindrome((b = b.add(reverse(b))).toString())) {
                return false;
            }
        }
        return true;
    }

    private BigDecimal reverse(BigDecimal b) {
        return new BigDecimal(new StringBuilder(b.toString()).reverse().toString());
    }

    public static boolean isPalindrome(String s) {
        return s.startsWith(new StringBuilder().append(s, s.length() / 2, s.length()).reverse().toString());
    }
}
