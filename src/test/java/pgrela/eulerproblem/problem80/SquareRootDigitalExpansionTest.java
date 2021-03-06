package pgrela.eulerproblem.problem80;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;

import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

public class SquareRootDigitalExpansionTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(SquareRootDigitalExpansion.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(40886);
    }
}
