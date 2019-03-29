package pgrela.eulerproblem.common;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(JUnitParamsRunner.class)
public class IntegersTest {

    @Test
    public void shouldCalculateLength() {
        //given
        int number = 7;

        //when
        int length = Integers.length(number);

        then(length).isEqualTo(1);
    }

    @Test
    @Parameters(
            {
                    "5,3",
                    "24,18",
                    "31,17"
            }
    )
    public void shouldCalculateExtendendEuclides(int a, int b) {

        Pair pair = Integers.extendedEuclidian(a, b);

        then(pair.a * a + pair.b * b).isEqualTo(Maths.gcd(a,b));
    }

}