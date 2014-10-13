package pgrela.eulerproblem.problem68;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.IntStream.*;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.problem24.LexicographicPermutations.getNthPermutation;

public class Magic5GonRing implements EulerSolver {

    public static void main(String[] args) {
        printSolution(Magic5GonRing.class);
    }

    public String solveToString() {
        int[] digits = rangeClosed(1, 9).toArray();
        return range(0, of(digits).reduce(1, (a, b) -> a * b))
                .mapToObj(i -> getNthPermutation(of(digits).boxed().collect(Collectors.<Integer>toList()), i))
                .filter(v -> v.get(0) + v.get(1) == v.get(3) + v.get(4))
                .filter(v -> v.get(2) + v.get(3) == v.get(5) + v.get(6))
                .filter(v -> v.get(4) + v.get(5) == v.get(7) + v.get(8))
                .filter(v -> v.get(6) + v.get(7) == 10 + v.get(0))
                .filter(v -> v.get(8) + 10 == v.get(1) + v.get(2))
                .map(v -> v.stream().map(String::valueOf).collect(Collectors.<String>toList()))
                .map(v -> {
                    v.add(9,"10");
                    int shift = range(0,4).map(i->i*2+1).boxed().min((a,b)->v.get(a).compareTo(v.get(b))).get()-1;
                    String[] s=range(0,10).mapToObj(i->v.get((i+shift)%10)).toArray(String[]::new);
                    return Stream.of(
                            s[1] + s[2] + s[0],
                            s[9] + s[0] + s[8],
                            s[7] + s[8] + s[6],
                            s[5] + s[6] + s[4],
                            s[3] + s[4] + s[2]
                    ).collect(Collectors.joining());
                }).max(String::compareTo).get();
    }
}
