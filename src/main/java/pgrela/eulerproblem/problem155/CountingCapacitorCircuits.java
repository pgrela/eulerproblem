package pgrela.eulerproblem.problem155;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.LongFraction;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CountingCapacitorCircuits implements EulerSolver {

    private static final int CIRCUITS = 15;

    public static void main(String[] args) throws ParseException {
        printSolution(CountingCapacitorCircuits.class);
    }

    public long solve() {
        Map<Integer, Set<LongFraction>> circuits = new HashMap<>();
        Set<LongFraction> allKnownCircuits = new HashSet<>();
        for (int i = 1; i <= CIRCUITS; i++) {
            circuits.put(i, new HashSet<>());
        }
        allKnownCircuits.add(LongFraction.ONE);
        circuits.get(1).addAll(allKnownCircuits);
        for (int totalCircuits = 2; totalCircuits <= CIRCUITS; totalCircuits++) {
            Set<LongFraction> targetSet = circuits.get(totalCircuits);
            for (int firstSize = 1; firstSize <= (totalCircuits + 1) / 2; firstSize++) {
                int otherSize = totalCircuits - firstSize;
                for (LongFraction firstCircuit : circuits.get(firstSize)) {
                    for (LongFraction otherCircuit : circuits.get(otherSize)) {
                        {
                            LongFraction maybeNew = firstCircuit.add(otherCircuit);
                            if (allKnownCircuits.add(maybeNew)) {
                                targetSet.add(maybeNew);
                            }
                        }
                        {
                            LongFraction maybeNew = firstCircuit.inverse().add(otherCircuit.inverse()).inverse();
                            if (allKnownCircuits.add(maybeNew)) {
                                targetSet.add(maybeNew);
                            }
                        }
                    }
                }
            }
        }
        return allKnownCircuits.size();
    }
}

