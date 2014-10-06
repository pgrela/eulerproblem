package pgrela.eulerproblem.problem50;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;

import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

public class ConsecutivePrimeSumTest {

    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(ConsecutivePrimeSum.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(997651);
    }

}