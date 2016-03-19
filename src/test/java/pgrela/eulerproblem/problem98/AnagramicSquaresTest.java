package pgrela.eulerproblem.problem98;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

import static org.assertj.core.api.BDDAssertions.then;

public class AnagramicSquaresTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(AnagramicSquares.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(18769);
    }
}