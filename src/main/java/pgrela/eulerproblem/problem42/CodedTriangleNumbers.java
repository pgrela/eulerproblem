package pgrela.eulerproblem.problem42;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static java.util.stream.Stream.of;
import static pgrela.eulerproblem.common.Files.getFileAsString;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CodedTriangleNumbers implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem42/words.txt";

    public static void main(String[] args) {
        printSolution(CodedTriangleNumbers.class);
    }

    public long solve() {
        return of(getFileAsString(RESOURCE_FILE).replace("\"", "").split(",")).map(s -> s.chars().map(c -> c - 'A' + 1).sum()).filter(Maths::isTriangleNumber).count();
    }

}
