package pgrela.eulerproblem.problem61;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.FiniteStreamBuilder.streamFrom;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CyclicalFigurateNumbers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CyclicalFigurateNumbers.class);
    }

    public long solve() {

        int[] triangle = streamFrom(1).of(n -> n*(n+1)/2).until(i->i<10000).stream().filter(i->i>999).mapToInt(i -> i).toArray();
        int[] square = streamFrom(1).of(n -> n*n).until(i->i<10000).stream().mapToInt(i -> i).toArray();
        int[] pentagonal = streamFrom(1).of(n -> n*( 3*n -1)/2).until(i -> i < 10000).stream().mapToInt(i -> i).toArray();
        int[] hexagonal = streamFrom(1).of(n -> n * (2 * n-1)).until(i->i<10000).stream().mapToInt(i -> i).toArray();
        int[] heptagonal = streamFrom(1).of(n -> n*(5*n-3)/2).until(i->i<10000).stream().mapToInt(i -> i).toArray();
        int[] octagonal = streamFrom(1).of(n -> n * (3 * n-2)).until(i->i<10000).stream().mapToInt(i -> i).toArray();
        return 0;
    }
}

