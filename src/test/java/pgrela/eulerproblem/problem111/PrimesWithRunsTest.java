package pgrela.eulerproblem.problem111;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pgrela.eulerproblem.common.ClassFactory;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(JUnitParamsRunner.class)
public class PrimesWithRunsTest {
    @Test
    @Parameters({
            "4,273700",
            "10,612407567715"
    })
    public void shouldSolveTheProblem(int digits, long expected) {
        //given
        PrimesWithRuns solver = ClassFactory.getObjectOf(PrimesWithRuns.class);

        //when
        long result = solver.solveForDigits(digits);

        then(result).isEqualTo(expected);
    }

}