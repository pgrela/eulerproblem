package pgrela.eulerproblem.problem66;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

import static org.assertj.core.api.BDDAssertions.then;

public class DiophantineEquationTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(DiophantineEquation.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(661);
    }
}
