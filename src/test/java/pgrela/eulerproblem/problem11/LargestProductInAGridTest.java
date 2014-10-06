package pgrela.eulerproblem.problem11;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

import static org.assertj.core.api.BDDAssertions.then;

public class LargestProductInAGridTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(LargestProductInAGrid.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(70600674);
    }
}
