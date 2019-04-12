package pgrela.eulerproblem.problem179;

import java.util.stream.IntStream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ConsecutivePositiveDivisors implements EulerSolver {

    public static void main(String[] args) {
        printSolution(ConsecutivePositiveDivisors.class);
    }

    public static final int MAX = Maths.pow(10, 7);

    @Override
    public long solve() {
        int[] divisors = new int[MAX];
        for (int n = 1; n < MAX; n++) {
            for (int multiple = 0; multiple < MAX; multiple += n) {
                ++divisors[multiple];
            }
        }
        return IntStream.range(1, MAX).filter(n -> divisors[n] == divisors[n - 1]).count();
    }

}

