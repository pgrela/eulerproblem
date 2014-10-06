package pgrela.eulerproblem.problem24;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(JUnitParamsRunner.class)
public class LexicographicPermutationsTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(LexicographicPermutations.class);

        //when
        String result = solver.solveToString();

        then(result).isEqualTo("2783915460");
    }

    @Test
    @Parameters({
            "10,0,0123456789"
    })
    public void shouldComputeSumOfDivisors(int numbers, int nthPaermutation, String expected) {
        //given
        LexicographicPermutations solver = ClassFactory.getObjectOf(LexicographicPermutations.class);

        //when
        String permutation = solver
                .getNthPermutation(IntStream.range(0, numbers).boxed().collect(Collectors.toList()), nthPaermutation)
                .stream()
                .reduce(new StringBuilder(), StringBuilder::append, (r, s) -> r.append(s)).toString();

        then(permutation).isEqualTo(expected);
    }
}
