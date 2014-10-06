package pgrela.eulerproblem.problem21;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import static java.util.stream.IntStream.*;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class AmicableNumbers implements EulerSolver {
    public static void main(String[] args) {
        printSolution(AmicableNumbers.class);
    }

    public long solve() {
        return range(2, 10000).filter(this::isAmicable).sum();
    }

    protected boolean isAmicable(int number) {
        int sumOfDivisors = Primes.sumDivisors(number);
        return sumOfDivisors != number && Primes.sumDivisors(sumOfDivisors) == number;
    }

}
