package pgrela.eulerproblem.problem176;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.text.ParseException;
import java.util.*;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RightAngledTrianglesThatShareACathetus implements EulerSolver {

    public static void main(String[] args) throws ParseException {
        printSolution(RightAngledTrianglesThatShareACathetus.class);
    }

    Map<Long, Integer> cache = new HashMap<>();

    @Override
    public long solve() {
        PriorityQueue<Number> numbers = new PriorityQueue<>();
        Set<Number> addedNumbers = new HashSet<>();
        numbers.add(Number.ONE);
        addedNumbers.add(Number.ONE);
        Number number;
        int c = 0;
        do {
            number = numbers.poll();
            ++c;
            number.increase().stream().filter(addedNumbers::add).forEach(numbers::add);
        } while (countTrianglesFast(number.getTwos(), number.getMultiplicity()) != 47547);
        System.out.println(c);
        return number.toNumber();
    }

    private long countTrianglesSlowlyButSelfExplanatory(Number number) {
        long aNumber = number.getNumber();
        long first = 0;
        if (number.getTwos() > 0) {
            long theNumberByTwo = aNumber / 2;
            // 2*n*(n+r)*k
            first = Primes.allDivisors(theNumberByTwo)
                    .filter(k -> k != theNumberByTwo)
                    .map(k -> theNumberByTwo / k)
                    .filter(N -> N % 2 == 0)
                    .map(N -> (1 << (Primes.getPrimeFactorsHistogram(N).size() - 1)))
                    .sum();
        }
        long theNumberWithout2s = aNumber>>number.getTwos();
        // r*(2n+r)*k
        long second = Primes.allDivisors(theNumberWithout2s)
                .filter(k -> k != theNumberWithout2s)
                .map(k -> theNumberWithout2s / k)
                .map(N -> (1 << (Primes.getPrimeFactorsHistogram(N).size() - 1)))
                .sum();
        return first + second;
    }

    private long countTrianglesFast(int twos, int[] multiplicity) {
        long counter = 0;
        int factors = Arrays.stream(multiplicity).sum();
        int[] primorial = new int[factors];
        {
            int factor = 0;
            for (int times = 0; times < multiplicity.length; times++) {
                int suchFactors = multiplicity[times];
                while (suchFactors-- > 0) {
                    primorial[factor++] = times;
                }
            }
        }
        for (int mask = 0; mask < (1 << factors); mask++) {
            int combinations = 1;
            for (int i = 0; i < factors; i++) {
                if (((mask >> i) & 1) == 1) {
                    combinations *= primorial[i];
                }
            }
            int present = ones(mask);
            counter += combinations * (1 << present) / 2;
            if (twos > 1) {
                counter += combinations * (twos - 1) * (1 << present);
            }
        }
        return counter;
    }

    int ones(int n) {
        int r = 0;
        while (n > 0) {
            if ((n & 1) == 1) ++r;
            n >>= 1;
        }
        return r;
    }

    static class Number implements Comparable<Number> {
        private static final int[] primes = Primes.primes(1000).toArray();
        int twos;
        int[] multiplicity;
        long number;
        public static Number ONE = new Number();

        private Number() {
            this.twos = 0;
            this.multiplicity = new int[0];
            this.number = toNumber();
        }

        public Number(int twos, int[] multiplicity) {
            this.twos = twos;
            this.multiplicity = multiplicity;
            this.number = toNumber();
        }

        List<Number> increase() {
            List<Number> increased = new ArrayList<>();
            increased.add(new Number(twos + 1, Arrays.copyOf(multiplicity, multiplicity.length)));
            for (int i = 0; i < multiplicity.length - 1; i++) {
                if (multiplicity[i] > 0) {
                    int[] copy = Arrays.copyOf(multiplicity, multiplicity.length);
                    --copy[i];
                    ++copy[i + 1];
                    increased.add(new Number(twos, copy));
                }
            }
            if (multiplicity.length > 0) {
                int[] copy = Arrays.copyOf(multiplicity, multiplicity.length + 1);
                --copy[multiplicity.length - 1];
                ++copy[multiplicity.length];
                increased.add(new Number(twos, copy));
                copy = Arrays.copyOf(multiplicity, multiplicity.length);
                ++copy[1];
                increased.add(new Number(twos, copy));
            } else {
                int[] copy = new int[2];
                copy[1] = 1;
                increased.add(new Number(twos, copy));
            }
            return increased;
        }

        private long toNumber() {
            long r = 1L << twos;
            int prime = 0;
            for (int times = multiplicity.length - 1; times >= 0; times--) {
                int factors = multiplicity[times];
                while (factors-- > 0) {
                    r *= Maths.pow(primes[++prime], (long) times);
                }
            }
            return r;
        }

        public long getNumber() {
            return number;
        }

        @Override
        public int compareTo(Number other) {
            return Long.compare(this.number, other.number);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Number)) return false;

            Number number1 = (Number) o;

            return number == number1.number;
        }

        @Override
        public int hashCode() {
            return (int) (number ^ (number >>> 32));
        }

        @Override
        public String toString() {
            return "Number{" +
                    "twos=" + twos +
                    ", multiplicity=" + Arrays.toString(multiplicity) +
                    ", number=" + number +
                    '}';
        }

        public int getTwos() {
            return twos;
        }

        public int[] getMultiplicity() {
            return multiplicity;
        }
    }
}

