package pgrela.eulerproblem.problem49;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

import static org.assertj.core.api.BDDAssertions.then;

public class PrimePermutationsTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(PrimePermutations.class);

        //when
        long result = Long.valueOf(solver.solveToString());

        then(result).isEqualTo(296962999629L);
    }
}
