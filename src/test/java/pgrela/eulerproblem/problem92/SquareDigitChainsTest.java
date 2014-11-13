package pgrela.eulerproblem.problem92;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;

import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

public class SquareDigitChainsTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(SquareDigitChains.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(8581146);
    }
}