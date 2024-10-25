package pgrela.eulerproblem.common;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.checkerframework.common.value.qual.IntRange;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@RunWith(JUnitParamsRunner.class)
public class LongsTest {
    @Test
    public void shouldRoot4() {
        then(Longs.sqrt(4)).isEqualTo(2);
    }
    @Test
    public void shouldRootPerfectSquares() {
        LongStream.range(0,100).forEach(n-> Assertions.assertThat(Longs.isPerfectSquare(n*n)).isTrue());
    }
    @Test
    @Parameters(
            {
                    "0,0",
                    "1,1",
                    "2,1",
                    "4,2",
                    "30,5",
                    "31,5",
                    "32,5",
                    "33,5",
                    "48,6",
                    "49,7",
                    "50,7",
                    "62,7",
                    "63,7",
                    "64,8",
                    "65,8",
            }
    )
    public void shouldRoot(int n, int expected) {
        then(Longs.sqrt(n)).isEqualTo(expected);
    }

    @Test
    public void shouldRootMax() {
        long max = Integer.MAX_VALUE;

        then(Longs.sqrt(max * max)).isEqualTo(max);
    }

    @Test
    public void shouldThrowForOverMaxInt() {
        long max = Integer.MAX_VALUE + 1L;

        Throwable throwable = catchThrowable(() -> Longs.sqrt(max * max));

        then(throwable).isInstanceOf(IllegalArgumentException.class);
    }
}