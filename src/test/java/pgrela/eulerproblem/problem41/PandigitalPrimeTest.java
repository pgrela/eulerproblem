package pgrela.eulerproblem.problem41;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

import static org.assertj.core.api.BDDAssertions.then;

public class PandigitalPrimeTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(PandigitalPrime.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(7652413);
    }
}
