package pgrela.eulerproblem.problem137;

import pgrela.eulerproblem.common.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class FibonacciGoldenNuggets implements EulerSolver {

    public static void main(String[] args) {
        printSolution(FibonacciGoldenNuggets.class);
    }

    public long solve() {
        int n = 1000;
        List<Long> observeFibonnacci = Coordinates.square(1, 1, n, n)
                .filter(p -> p.x < p.y)
                .map(Factor::new)
                .distinct()
                .sorted((x, y) -> signum(x.a * y.b - x.b * y.a))
                .filter(p -> (p.a * p.b) % (p.b * p.b - p.a * p.b - p.a * p.a) == 0)
                .filter(p -> (p.b * p.b - p.a * p.b - p.a * p.a) > 0)
                .peek(p -> System.out.println(p + "->" + (p.a * p.b) / (p.b * p.b - p.a * p.b - p.a * p.a)))
                .map(p -> p.a * p.b / (p.b * p.b - p.a * p.b - p.a * p.a))
                .collect(Collectors.toList());
        return Stream.iterate(new Pair(1, 2), p -> new Pair(p.a + p.b, p.a + p.b * 2))
                .skip(14)
                .map(p -> ((long) p.a) * p.b / (p.b * p.b - p.a * p.b - p.a * p.a))
                .findFirst().get();
    }

    static int signum(long n) {
        return n > 0 ? 1 : n < 0 ? -1 : 0;
    }

    public static class Factor {
        public long a, b;

        public Factor(Point p) {
            int gcd = Maths.gcd(p.x, p.y);
            a = p.x / gcd;
            b = p.y / gcd;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Factor factor = (Factor) o;

            if (a != factor.a) return false;
            return b == factor.b;
        }

        @Override
        public int hashCode() {
            int result = (int) (a ^ (a >>> 32));
            result = 31 * result + (int) (b ^ (b >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "{" + a + "," + b + '}';
        }
    }
}

