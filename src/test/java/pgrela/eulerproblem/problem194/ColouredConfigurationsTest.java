package pgrela.eulerproblem.problem194;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColouredConfigurationsTest {

    private ColouredConfigurations colouredConfigurations = new ColouredConfigurations();

    @Test
    public void shouldCountA3Segments() {
        long l = colouredConfigurations.possibleAContinuationWithColours(3);

        Assertions.assertThat(l).isEqualTo(4);
    }

    @Test
    public void should223() {
        long l = colouredConfigurations.combinations(2, 2, 3);

        Assertions.assertThat(l).isEqualTo(20736);
    }

    @Test
    public void should024() {
        long l = colouredConfigurations.combinations(0, 2, 4);

        Assertions.assertThat(l).isEqualTo(92928);
    }

    @Test
    public void should103() {
        long l = colouredConfigurations.combinations(1, 0, 3);

        Assertions.assertThat(l).isEqualTo(24);
    }

}