package pgrela.eulerproblem.problem32;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.problem37.TruncatablePrimes;

import static org.assertj.core.api.BDDAssertions.then;

public class PandigitalProductsTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(PandigitalProducts.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(45228);
    }
}
