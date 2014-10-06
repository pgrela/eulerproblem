package pgrela.eulerproblem.problem29;

import pgrela.eulerproblem.common.Coordinates;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Pair;

import java.util.HashMap;
import java.util.Map;

import static pgrela.eulerproblem.common.Point.point;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class DistinctPowers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(DistinctPowers.class);
    }

    @Override
    public long solve() {
        Map<Integer, Pair> powers = new HashMap<>();
        for (int i = 2; i < 100; i++) {
            if (powers.containsKey(i)) continue;
            int exponent = 2;
            int number = i * i;
            while (number <= 100) {
                powers.put(number, Pair.pair(i, exponent));
                ++exponent;
                number *= i;
            }
        }
        return Coordinates.square(2, 2, 100, 100).map(p -> powers.containsKey(p.x) ? point(powers.get(p.x).a, p.y * powers.get(p.x).b) : p).distinct().count();
    }
}
