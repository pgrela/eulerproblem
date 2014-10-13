package pgrela.eulerproblem.problem64;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Pair;

import java.util.HashMap;
import java.util.Map;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class OddPeriodSquareRoots implements EulerSolver {

    public static void main(String[] args) {
        printSolution(OddPeriodSquareRoots.class);
    }

    public long solve() {
        int sum = 0;
        for (int i = 2; i < 10000; i++) {
            Map<Pair, Integer> pastElements = new HashMap<>();

            int d = (int) Math.sqrt(i);
            if (d * d == i) continue;
            int minus = 0;
            int denominator = 1;
            int iteration = 0;
            while (true) {
                denominator = (i - minus * minus) / denominator;
                minus =  ((d + minus) / denominator * denominator)-minus;
                Pair pair = Pair.pair(minus, denominator);
                if (pastElements.containsKey(pair)) {
                    if (pastElements.get(pair) - (iteration) % 2 == 1) ++sum;
                    break;
                }
                pastElements.put(pair, iteration++);
            }
        }
        return sum;
    }
}

