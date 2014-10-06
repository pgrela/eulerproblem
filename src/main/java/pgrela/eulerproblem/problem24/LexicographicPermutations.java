package pgrela.eulerproblem.problem24;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class LexicographicPermutations implements EulerSolver {

    public static void main(String[] args) {
        printSolution(LexicographicPermutations.class);
    }

    @Override
    public String solveToString() {
        List< Integer > solution = getNthPermutation(IntStream.range(0, 10).boxed().collect(Collectors.toList()), 1000 * 1000-1);
        return solution.stream().reduce(new StringBuilder(), StringBuilder::append,(r,s)->r.append(s)).toString();
    }

    public static List<Integer> getNthPermutation(List<Integer> elements, int permutation) {
        int[] inFactorials = inFactorials(permutation, elements.size());
        List<Integer> solution = new ArrayList<>();
        for (int inFactorial : inFactorials) {
            solution.add(elements.get(inFactorial));
            elements.remove(inFactorial);
        }
        return solution;
    }

    public static int[] inFactorials(int number, int max) {
        int[] factorials = LongStream.range(0, max).map(Maths::factorial).mapToInt(n -> (int) n).toArray();
        int[] factorialized = new int[max];
        for (int i = 0; i < factorials.length; i++) {
            factorialized[i] = number / factorials[max - 1 - i];
            number -= factorialized[i] * factorials[max - 1 - i];
        }
        return factorialized;
    }
}
