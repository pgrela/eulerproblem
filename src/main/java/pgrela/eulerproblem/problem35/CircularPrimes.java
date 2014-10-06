package pgrela.eulerproblem.problem35;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.common.Streams.stream;
import static pgrela.eulerproblem.problem35.CircularPrimes.NumberRotator.rotationsStream;

public class CircularPrimes implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CircularPrimes.class);
    }

    @Override
    public long solve() {
        Set<Integer> primes = Primes.primes(1000000).boxed().collect(Collectors.toSet());
        return primes.stream().sequential().filter(p -> rotationsStream(p).allMatch(primes::contains)).count();
    }

    static class NumberRotator implements Iterator<Integer> {
        final int original;
        Integer current = null;
        boolean rotated = false;
        final int power;

        NumberRotator(int original) {
            this.original = original;
            current=original;
            this.power = Maths.pow(10, Integers.length(original) - 1);
        }

        @Override
        public boolean hasNext() {
            return !rotated || original != current;
        }

        @Override
        public Integer next() {
            rotated = true;
            current = current % 10 * power + current / 10;
            return current;
        }

        public static Stream<Integer> rotationsStream(int number){
            return stream(new NumberRotator(number));
        }

    }
}
