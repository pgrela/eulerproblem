package pgrela.eulerproblem.common;

import java.io.IOException;

public interface EulerSolver {
    default long solve() {
        throw new RuntimeException("Not implemented");
    }
    default String solveToString(){
        return String.valueOf(solve());
    }
}
