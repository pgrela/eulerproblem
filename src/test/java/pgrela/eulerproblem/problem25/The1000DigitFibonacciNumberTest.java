package pgrela.eulerproblem.problem25;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(JUnitParamsRunner.class)
public class The1000DigitFibonacciNumberTest {

        @Test
        public void shouldSolveTheProblem() {
            //given
            EulerSolver solver = ClassFactory.getObjectOf(The1000DigitFibonacciNumber.class);

            //when
            String result = solver.solveToString();

            then(result).isEqualTo("4782");
        }

        @Test
        @Parameters({
                "5,21",
                "3,12"
        })
        public void shouldComputeWhatFibonacciNumberIsOfGivenLength(int givenLength, int expectedIndex) {
            //given
            The1000DigitFibonacciNumber solver = ClassFactory.getObjectOf(The1000DigitFibonacciNumber.class);

            //when
            long length = solver.fibonacciFirstIndexForGivenElementLength(givenLength);

            then(length).isEqualTo(expectedIndex);
        }
}
