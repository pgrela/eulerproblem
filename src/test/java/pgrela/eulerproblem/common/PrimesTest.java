package pgrela.eulerproblem.common;

import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class PrimesTest {
    @Test
    public void shouldComputeAllDivisors() {
        //given

        //when
        int[] factors = Primes.allDivisors(24).toArray();

        then(factors).containsOnly(1, 2, 3, 4, 6, 8, 12, 24);
    }

    @Test
    public void shouldComputeSumOfAllDivisors() {
        //given

        //when
        int sum = Primes.sumDivisors(24);

        then(sum).isEqualTo(1 + 2 + 3 + 4 + 6 + 8 + 12);
    }

}