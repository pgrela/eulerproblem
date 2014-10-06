package pgrela.eulerproblem.problem3;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;

import static org.assertj.core.api.BDDAssertions.then;

public class LargestPrimeFactorTest {

    @Test
    public void shouldSolveTheProblem() {
        //given
        LargestPrimeFactor solver = ClassFactory.getObjectOf(LargestPrimeFactor.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(6857);
    }
}
