package pgrela.eulerproblem.problem44;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

import static org.assertj.core.api.BDDAssertions.then;

public class PentagonNumbersTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(PentagonNumbers.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(5482660);
    }
}
