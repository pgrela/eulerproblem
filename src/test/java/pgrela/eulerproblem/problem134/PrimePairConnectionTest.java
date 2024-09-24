package pgrela.eulerproblem.problem134;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pgrela.eulerproblem.common.Pair;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(JUnitParamsRunner.class)
public class PrimePairConnectionTest {

    @Test
    @Parameters({"150,180,-1,1"})
    public void testExtendedEuclides(int a, int b, int x, int y) throws Exception {
        //given

        //when
        Pair extendedEuclides = PrimePairConnection.extendedEuclides(a, b);

        //then(extendedEuclides.a).isEqualTo(x);
        //then(extendedEuclides.b).isEqualTo(y);

    }
}