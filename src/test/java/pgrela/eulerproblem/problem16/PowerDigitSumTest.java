package pgrela.eulerproblem.problem16;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.problem11.LargestProductInAGrid;

import static org.assertj.core.api.BDDAssertions.then;

public class PowerDigitSumTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(PowerDigitSum.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(1366);
    }
}
