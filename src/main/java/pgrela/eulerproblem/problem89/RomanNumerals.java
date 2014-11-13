package pgrela.eulerproblem.problem89;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Files;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RomanNumerals implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem89/roman.txt";

    public static void main(String[] args) {
        printSolution(RomanNumerals.class);
    }

    public long solve() {
        return Files.getLinesStream(RESOURCE_FILE)
                .mapToInt(s ->(s.matches(".*IIII.*") ? 2 : 0)
                                        + (s.matches(".*XXXX.*") ? 2 : 0)
                                        + (s.matches(".*CCCC.*") ? 2 : 0)
                                        + (s.matches(".*VIIII.*") ? 1 : 0)
                                        + (s.matches(".*LXXXX.*") ? 1 : 0)
                                        + (s.matches(".*DCCCC.*") ? 1 : 0)
                ).sum();
    }
}
