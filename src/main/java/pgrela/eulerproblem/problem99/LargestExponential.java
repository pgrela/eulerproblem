package pgrela.eulerproblem.problem99;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.stream.IntStream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Files;

public class LargestExponential implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem99/base_exp.txt";

    public static void main(String[] args) {
        printSolution(LargestExponential.class);
    }

    public long solve() {
        double[] lengths = Files.getLinesStream(RESOURCE_FILE)
                .map(s->s.split(","))
                .mapToDouble(a->Double.valueOf(a[1])*Math.log(Double.valueOf(a[0])))
                .toArray();
        return IntStream.range(0,1000).boxed().max((a, b) -> Double.compare(lengths[a], lengths[b])).get();
    }

}
