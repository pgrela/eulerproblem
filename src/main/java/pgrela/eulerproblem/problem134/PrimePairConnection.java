package pgrela.eulerproblem.problem134;

import pgrela.eulerproblem.common.*;

import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PrimePairConnection implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PrimePairConnection.class);
    }

    public static Pair extendedEuclides(int a, int b) {
        return extendedEuclides(a, b, 1, 0);
    }

    public long solve() {
        System.out.println(18613426664617116L - 1000003 + 5);
        int[] primes = Primes.primes(Integers.ONE_MILLION + 1000).toArray();
        return FiniteStreamBuilder.streamFrom(2).of(i -> i + 1).until(i -> primes[i] < 10000).stream().parallel()
                .peek(i -> {
                    if (i % 10000 == 0) System.out.println(i);
                })
                .mapToLong(ip1 -> {
                            long tensByP1 = Maths.pow(10, Integers.length(primes[ip1]));
                            long diff = primes[ip1 + 1] - primes[ip1];
                            long gcd = Maths.gcd(tensByP1, diff);
                            long tensGcd = tensByP1 / gcd;
                            long diffGcd = diff / gcd;
                            long kn = LongStream.iterate(0, i -> i + 1).
                                    filter(x -> (diffGcd + primes[ip1 + 1] * x) % tensGcd == 0)
                                    .map(x -> (diffGcd + primes[ip1 + 1] * x) / tensGcd)
                                    .findFirst()
                                    .getAsLong();
                            System.out.println(primes[ip1 + 1] + " -> " + kn + ", tens=" + tensGcd + ", diff=" + diffGcd);
                            return kn * tensByP1 + primes[ip1];
                        }
                )
                .sum();
    }

    public static Pair extendedEuclides(int a, int b, int x, int y) {
        if (b != 0) {
            extendedEuclides(b, a % b, x, y);
            int pom = y;
            y = x - a / b * y;
            x = pom;
        }
    }

}
