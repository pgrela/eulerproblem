package pgrela.eulerproblem.problem483;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pgrela.eulerproblem.common.EulerSolver;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(JUnitParamsRunner.class)
public class RepeatedPermutationTest {

    @Test
    @Parameters({
            "3,5.166666667e0",
            "5,1.734166667e1",
            "20,5.106136147e3"
    })
    public void shouldSolveTestCasesFromExampleOnWeb(int length, String expectedValue) {
            //given
            EulerSolver solver = new RepeatedPermutation(length);

            //when
            String value = solver.solveToString();

            then(value).isEqualTo(expectedValue);
    }
}
