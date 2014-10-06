package pgrela.eulerproblem.problem26;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ReciprocalCycles implements EulerSolver {

    public static void main(String[] args) {
        printSolution(ReciprocalCycles.class);
    }

    @Override
    public long solve() {
        return range(2, 1000).reduce(2, (a, b) -> reciprocalCycleLength(a) > reciprocalCycleLength(b) ? a : b);
    }

    protected int reciprocalCycleLength(int oneBy) {
        Map<Integer, Integer> visited = new HashMap<>();
        int modulo = 1;
        int step = 0;
        while (true) {
            if (visited.containsKey(modulo)) {
                return step - visited.get(modulo);
            }
            visited.put(modulo, step);
            ++step;
            modulo = modulo * 10 % oneBy;
        }
    }
}
