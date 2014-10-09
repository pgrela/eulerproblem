package pgrela.eulerproblem.problem483;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.TestCase;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import pgrela.eulerproblem.common.EulerSolver;

@RunWith(JUnitParamsRunner.class)
public class RepeatedPermutationByPrimesTest extends TestCase {

    @Test
    @Parameters({
            "3,3,1,2",
            "3,2,1,3"
    })
    public void testCycleCounter(int permutationLength, int cycleLength, int quantity,
                             double expectedCount) throws Exception {
        //given
        RepeatedPermutationByPrimes solver = new RepeatedPermutationByPrimes();

        //when
        double divisorsCount = solver.countCycles(permutationLength, cycleLength, quantity);

        then(divisorsCount).isEqualTo(expectedCount);
    }

    @Test
    @Parameters({
            "3,5.166666667e0",
            "5,1.734166667e1",
            "20,5.106136147e3"
    })
    public void shouldSolveTestCasesFromExampleOnWeb(int length, String expectedValue) {
        //given
        EulerSolver solver = new RepeatedPermutationByPrimes(length);

        //when
        String value = solver.solveToString();

        then(value).isEqualTo(expectedValue);
    }
}