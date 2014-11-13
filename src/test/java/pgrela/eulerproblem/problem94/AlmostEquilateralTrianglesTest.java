package pgrela.eulerproblem.problem94;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

import static org.assertj.core.api.BDDAssertions.then;

public class AlmostEquilateralTrianglesTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(AlmostEquilateralTriangles.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(518408346);
    }
}