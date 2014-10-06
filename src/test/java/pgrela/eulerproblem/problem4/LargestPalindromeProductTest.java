package pgrela.eulerproblem.problem4;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.Maths;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(JUnitParamsRunner.class)
public class LargestPalindromeProductTest {
    @Test
    @Parameters({
            "1",
            "2",
            "22",
            "232",
            "1221"
    })
    public void shouldRecognizePalindromes(int number) {
        //given
        LargestPalindromeProduct solver = ClassFactory.getObjectOf(LargestPalindromeProduct.class);

        //when
        boolean isPalindrome = Maths.isPalindrome(number);

        then(isPalindrome).isTrue();
    }
    @Test
    @Parameters(
            {"12","21","221","122","900900","914190"}
    )
    public void shouldRecognizeNonPalindromes(int number) {
        //given
        LargestPalindromeProduct solver = ClassFactory.getObjectOf(LargestPalindromeProduct.class);

        //when
        boolean isPalindrome = Maths.isPalindrome(number);

        then(isPalindrome).isFalse();
    }
    @Test
    public void shouldSolveTheProblem() {
        //given
        LargestPalindromeProduct solver = ClassFactory.getObjectOf(LargestPalindromeProduct.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(906609);
    }
}
