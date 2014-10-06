package pgrela.eulerproblem.problem22;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.Arrays;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.Files.getFileAsString;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class NamesScores implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem22/names.txt";

    public static void main(String[] args) {
        printSolution(NamesScores.class);
    }

    public long solve() {
        String[] names = getFileAsString(RESOURCE_FILE).replace("\"", "").split(",");
        Arrays.sort(names);
        return IntStream.range(0, names.length).map(i -> (i + 1) * names[i].chars().map(c -> c - 'A' + 1).reduce(0, Integer::sum)).sum();
    }

}
