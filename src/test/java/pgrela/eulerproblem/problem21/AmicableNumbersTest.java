package pgrela.eulerproblem.problem21;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(JUnitParamsRunner.class)
public class AmicableNumbersTest {

    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(AmicableNumbers.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(31626);
    }
    @Test
    @Parameters({
            "2,1",
            "3,1",
            "4,3",
            "220,284"
    })
    public void shouldComputeSumOfDivisors(int number, int sumOfDivisors) {
        //when
        int sum = Primes.sumDivisors(number);

        then(sum).isEqualTo(sumOfDivisors);
    }
    @Test
    @Parameters({
            "220","284"
    })
    public void shouldRecognizeAmicable(int number) {
        //given
        AmicableNumbers solver = ClassFactory.getObjectOf(AmicableNumbers.class);

        //when
        boolean isAmicable = solver.isAmicable(number);

        then(isAmicable).isTrue();
    }
}
