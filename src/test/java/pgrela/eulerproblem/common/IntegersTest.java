package pgrela.eulerproblem.common;

import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class IntegersTest {

    @Test
    public void shouldCalculateLength(){
        //given
        int number = 7;

        //when
        int length = Integers.length(number);

        then(length).isEqualTo(1);
    }

}