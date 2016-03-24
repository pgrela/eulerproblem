package pgrela.eulerproblem.problem134;

import pgrela.eulerproblem.common.*;

import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PrimePairConnection implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PrimePairConnection.class);
    }

    public long solve() {
        System.out.println(18613426664617116L-1000003+5);
        int[] primes = Primes.primes(Integers.ONE_MILLION + 1000).toArray();
        return FiniteStreamBuilder.streamFrom(2).of(i -> i + 1).until(i -> primes[i] < 1000000).stream()
                .peek(i -> {
                    if (i % 10000 == 0) System.out.println(i);
                })
                .mapToLong(ip1 -> {
                            long tensByP1 = Maths.pow(10, Integers.length(primes[ip1]));
                            long diff = primes[ip1 + 1] - primes[ip1];
                            long gcd = Maths.gcd(tensByP1, diff);
                            long kn = LongStream.iterate(0, i -> i + 1).
                                    filter(k -> (k * tensByP1/gcd - diff/gcd) % primes[ip1 + 1] == 0)
                                    .findFirst()
                                    .getAsLong();
                            //System.out.println(primes[ip1 + 1] + " -> " + kn);
                            return kn * tensByP1 + primes[ip1 + 1];
                        }
                )
                .sum();
    }

}
