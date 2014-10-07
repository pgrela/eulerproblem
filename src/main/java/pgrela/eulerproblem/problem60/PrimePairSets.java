package pgrela.eulerproblem.problem60;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import static java.util.stream.IntStream.of;
import static pgrela.eulerproblem.common.Integers.length;
import static pgrela.eulerproblem.common.Maths.pow;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PrimePairSets implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PrimePairSets.class);
    }

    public long solve() {
        int[] primes = Primes.primes(10000).toArray();
        int numbers=5;
        int[] indices = new int[]{0, 1, 2, 3, 3, primes.length};
        out:
        while (true) {
            increaseIndices(indices);
            if (of(indices).limit(numbers).distinct().count() != numbers) continue;
            int[] chosen = of(indices).limit(numbers).map(i -> primes[i]).toArray();
            for (int i = 1; i < chosen.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (!Primes.isPrime(concat(chosen[i], chosen[j]))) continue out;
                    if (!Primes.isPrime(concat(chosen[j], chosen[i]))) continue out;

                }
            }
            return of(chosen).peek(System.out::println).sum();
        }
    }

    private int concat(int a, int b) {
        return a * pow(10, length(b)) + b;
    }

    private void increaseIndices(int[] indices) {
        int currentDigit = indices.length - 2;
        while (true) {
            ++indices[currentDigit];
            if (indices[currentDigit] == indices[currentDigit + 1]) {
                --indices[currentDigit];
                --currentDigit;
            }else break;
        }
        for (int i = currentDigit+1; i < indices.length-1; i++) {
            indices[i]=indices[i-1]+1;
        }
    }
}
