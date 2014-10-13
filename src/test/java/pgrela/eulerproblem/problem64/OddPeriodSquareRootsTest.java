package pgrela.eulerproblem.problem64;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

import static org.assertj.core.api.BDDAssertions.then;

public class OddPeriodSquareRootsTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(OddPeriodSquareRoots.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(1322);
    }
}
