package pgrela.eulerproblem.common;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface EulerSolver {
    public default long solve(){
        throw new NotImplementedException();
    }
    public default String solveToString(){
        return String.valueOf(solve());
    }
}
