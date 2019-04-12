package pgrela.eulerproblem.common;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(JUnitParamsRunner.class)
public class MathsTest {

    @Test
    @Parameters(
            {
                    "1,1,true",
                    "4,2,true",
                    "32,5,true",
                    "32,6,false",
                    "3,2,false",
                    "3,1,true",
                    "1,-1,true"
            }
    )
    public void shouldDetectPowers(int n, int exponent, boolean isPow) {
        Assertions.assertThat(Maths.isPow(n, exponent)).isEqualTo(isPow);
    }

}