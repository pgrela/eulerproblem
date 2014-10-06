package pgrela.eulerproblem.problem45;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.problem44.PentagonNumbers;

import static org.assertj.core.api.BDDAssertions.then;

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
