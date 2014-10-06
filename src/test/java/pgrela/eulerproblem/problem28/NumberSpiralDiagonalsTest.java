package pgrela.eulerproblem.problem28;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(JUnitParamsRunner.class)
public class NumberSpiralDiagonalsTest {

    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(NumberSpiralDiagonals.class);

        //when
        String result = solver.solveToString();

        then(result).isEqualTo("669171001");
    }

    @Test
    @Parameters({
            "1,25",
            "2,101"
    })
    public void shouldComputeWhatFibonacciNumberIsOfGivenLength(int givenRadius, int expectedDiagonalSum) {
        //given
        NumberSpiralDiagonals solver = ClassFactory.getObjectOf(NumberSpiralDiagonals.class);

        //when
        long length = solver.solve(givenRadius);

        then(length).isEqualTo(expectedDiagonalSum);
    }
}