package pgrela.eulerproblem.problem45;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;

import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

public class TriangularPentagonalAndHexagonalTest {

    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(TriangularPentagonalAndHexagonal.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(1533776805L);
    }
}
