package pgrela.eulerproblem.problem60;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.problem50.ConsecutivePrimeSum;

import static org.assertj.core.api.BDDAssertions.then;

public class PrimePairSetsTest {

    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(PrimePairSets.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(26033);
    }
}
