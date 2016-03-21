package pgrela.eulerproblem.problem127;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ABCHits implements EulerSolver {
    public static void main(String[] args) {
        printSolution(ABCHits.class);
    }

    public long solve() {
        return IntStream.range(1, 1000).map(c ->
                (int)IntStream.range(1, c/2)
                        .filter(b -> Maths.gcd(c, b) == 1)
                        .filter(b -> Maths.gcd(c, c - b) == 1)
                        .filter(b -> Maths.gcd(b, c - b) == 1)
                        .filter(b -> Primes.getPrimeFactorsHistogram((c - b) * b * c, Primes.getPrimesToFactorizeInteger())
                                .keySet()
                                .stream()
                                .reduce(1, (x, y) -> x * y) < c
                        )
                        .peek(a->System.out.println(a+" "+(c-a)+" "+c))
                        .count()*c
        ).sum();
    }

}
