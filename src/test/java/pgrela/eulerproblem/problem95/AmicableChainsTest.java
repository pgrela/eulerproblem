package pgrela.eulerproblem.problem95;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

import static org.assertj.core.api.BDDAssertions.then;

public class AmicableChainsTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(AmicableChains.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(14316);
    }
}