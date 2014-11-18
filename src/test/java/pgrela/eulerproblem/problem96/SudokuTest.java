package pgrela.eulerproblem.problem96;

import org.junit.Test;
import pgrela.eulerproblem.common.ClassFactory;
import pgrela.eulerproblem.common.EulerSolver;

import static org.assertj.core.api.BDDAssertions.then;

public class SudokuTest {
    @Test
    public void shouldSolveTheProblem() {
        //given
        EulerSolver solver = ClassFactory.getObjectOf(Sudoku.class);

        //when
        long result = solver.solve();

        then(result).isEqualTo(24702);
    }

}