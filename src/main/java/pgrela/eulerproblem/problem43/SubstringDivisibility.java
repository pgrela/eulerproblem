package pgrela.eulerproblem.problem43;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.problem24.LexicographicPermutations.getNthPermutation;

public class SubstringDivisibility implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SubstringDivisibility.class);
    }

    public long solve() {
        List<Integer> digits = IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toList());
        return getAllPermutationsAsList(digits)
                .filter(list->list.get(0)!=0)
                .filter(list -> numberFromDigits(list, 1, 2, 3) % 2 == 0)
                .filter(list -> numberFromDigits(list, 2, 3, 4) % 3 == 0)
                .filter(list -> numberFromDigits(list, 3, 4, 5) % 5 == 0)
                .filter(list -> numberFromDigits(list, 4, 5, 6) % 7 == 0)
                .filter(list -> numberFromDigits(list, 5, 6, 7) % 11 == 0)
                .filter(list -> numberFromDigits(list, 6, 7, 8) % 13 == 0)
                .filter(list -> numberFromDigits(list, 7, 8, 9) % 17 == 0)
                .mapToLong(SubstringDivisibility::digitsToLong)
                .peek(System.out::println)
                .sum();
    }

    public int numberFromDigits(List<Integer> list, int... digits) {
        int r = 0;
        for (int digit : digits) {
            r = r * 10 + list.get(digit);
        }
        return r;
    }


    public static IntStream getAllPermutations(List<Integer> digits) {
        return getAllPermutationsAsList(digits).mapToInt(SubstringDivisibility::digitsToInt);
    }

    public static Stream<List<Integer>> getAllPermutationsAsList(List<Integer> digits) {
        return range(0, Maths.factorial(digits.size())).boxed()
                .map(n -> getNthPermutation(new ArrayList<>(digits), n));
    }

    public static int digitsToInt(List<Integer> digits) {
        int r = 0;
        for (int digit : digits) {
            r = r * 10 + digit;
        }
        return r;
    }
    public static long digitsToLong(List<Integer> digits) {
        long r = 0;
        for (int digit : digits) {
            r = r * 10 + digit;
        }
        return r;
    }
}
