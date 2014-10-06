package pgrela.eulerproblem.problem12;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.problem11.LargestProductInAGrid;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(JUnitParamsRunner.class)
public class HighlyDivisibleTriangularNumberTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(HighlyDivisibleTriangularNumber.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(76576500);
    }
    @Test
    @Parameters({
            "2,2",
            "6,4",
            "28,6",
            "16,5",
            "76576500,576"
    })
    public void testDivisors(int number, int expectedDivisorsCount) throws Exception {
        //given
        HighlyDivisibleTriangularNumber solver = new HighlyDivisibleTriangularNumber();

        //when
        long divisorsCount = solver.countDivisors(number);

        then(divisorsCount).isEqualTo(expectedDivisorsCount);
    }
}
