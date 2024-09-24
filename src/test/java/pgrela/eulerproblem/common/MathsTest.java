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

    @Test
    public void shouldComputeNewtonModulo() {
        long newtonModulo = Maths.newtonModulo(75, 25, 100_000_000);

        Assertions.assertThat(newtonModulo).isEqualTo(48893628L);
    }

    @Test
    public void shouldComputeNewtonModulo2() {
        long newtonModulo = Maths.newtonModulo(75, 50, 100_000_000);

        Assertions.assertThat(newtonModulo).isEqualTo(48893628L);
    }
}