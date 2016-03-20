package pgrela.eulerproblem.problem118;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.problem43.SubstringDivisibility.getAllPermutationsAsList;

public class PandigitalPrimeSets implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PandigitalPrimeSets.class);
    }

    public long solve() {

        //Brute force, sorry....

        int[] sizes = new int[10];
        List<Integer> digits = IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toList());
        getAllPermutationsAsList(digits).forEach(p ->
                IntStream.range(0, 1 << 8).forEach(combination -> {
                    int currentPossiblePrime = 0;
                    int colons = combination;
                    int size = 1;
                    boolean succeded = true;
                    for (int j = 0; j < 9; j++) {
                        currentPossiblePrime = currentPossiblePrime * 10 + p.get(j);
                        if ((colons & 1) == 1) {
                            if (!Primes.isPrime(currentPossiblePrime)) {
                                succeded = false;
                                break;
                            }
                            ++size;
                            currentPossiblePrime = 0;
                        }
                        colons >>= 1;
                    }
                    if (succeded && Primes.isPrime(currentPossiblePrime)) {
                        ++sizes[size];
                    }
                }));
        return IntStream.range(0, sizes.length)
                .map(s -> sizes[s] / Maths.factorial(s))
                .sum();
    }


}
