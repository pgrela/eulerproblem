package pgrela.eulerproblem.problem38;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.problem37.TruncatablePrimes;

import static org.assertj.core.api.BDDAssertions.then;

public class PandigitalMultiplesTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(PandigitalMultiples.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(932718654);
    }
}
