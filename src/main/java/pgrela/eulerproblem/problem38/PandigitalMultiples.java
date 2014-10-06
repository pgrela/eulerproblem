package pgrela.eulerproblem.problem38;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.problem32.PandigitalProducts;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PandigitalMultiples implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PandigitalMultiples.class);
    }

    @Override
    public long solve() {
        return IntStream.range(1,100000).mapToObj(
                n->{
                    StringBuilder s=new StringBuilder();
                    int i=0;
                    while (s.length()<9){
                        s.append(n*++i);
                    }
                    return s.toString();
                }
        ).filter(PandigitalProducts::isPandigital).max(String::compareTo).map(Integer::valueOf).get();
    }
}
