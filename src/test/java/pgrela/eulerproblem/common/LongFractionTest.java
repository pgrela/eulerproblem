package pgrela.eulerproblem.common;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LongFractionTest {

    @Test
    @Parameters(
            {
                    "1,1,1",
                    "1,1,-1",
                    "-1,1,-1",
                    "-1,-1,-1",
                    "-1,-1,1",
                    "1,2,5",
                    "3,5,7",
            }
    )
    public void shouldPowerAndRoot(int nominator, int denominator, int exponent) {
        // given
        LongFraction fraction = new LongFraction(nominator, denominator);

        // when
        LongFraction powedAndRooted = fraction.pow(exponent).root(exponent);

        // then
        Assertions.assertThat(powedAndRooted).isEqualTo(fraction);
    }

}