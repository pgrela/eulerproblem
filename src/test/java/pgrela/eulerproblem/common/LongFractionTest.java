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

    @Test
    @Parameters(
            {
                    "2,3,5,6,true",
                    "2,3,5,6,true",
                    "1,1,2,1,true",
                    "0,2,0,1,false",
                    "3,10,2,7,false",
            }
    )
    public void shouldIndicateSmaller(int nominator, int denominator, int otherNominator, int otherDenominator, boolean otherIsBigger) {
        // given
        LongFraction fraction = new LongFraction(nominator, denominator);
        LongFraction bigger = new LongFraction(otherNominator, otherDenominator);

        // when
        boolean lessThanOther = fraction.isLessThan(bigger);

        // then
        Assertions.assertThat(lessThanOther).isEqualTo(otherIsBigger);
    }

}