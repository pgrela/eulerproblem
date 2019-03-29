package pgrela.eulerproblem.problem159;

import mikera.util.Maths;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import java.text.ParseException;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class DigitalRootSumsOfFactorisations implements EulerSolver {

    public static final int N = 1_000_000;

    public static void main(String[] args) throws ParseException {
        printSolution(DigitalRootSumsOfFactorisations.class);
    }

    public long solve() {
        int[] ds = IntStream.rangeClosed(0, N).map(this::digitSum).toArray();
        int[] factorisations = new int[N + 1];
        return IntStream.range(2, N).map(i ->
                factorisations[i] = Maths.max(ds[i],
                        Primes.allDivisors(i)
                                .filter(d -> d != i)
                                .filter(d -> d != 1)
                                .map(d -> factorisations[i / d] + ds[d])
                                .max()
                                .orElse(0)
                )
        )
                .sum();
    }

    private int digitSum(int n) {
        if (n < 10) return n;
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return digitSum(sum);
    }
}

