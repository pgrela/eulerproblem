package pgrela.eulerproblem.problem80;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.problem66.DiophantineEquation;

import static org.assertj.core.api.BDDAssertions.then;

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
