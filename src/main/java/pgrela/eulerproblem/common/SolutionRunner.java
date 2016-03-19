package pgrela.eulerproblem.common;

public class SolutionRunner {
    public static void printSolution(EulerSolver solver) {
        solve(solver);
    }
    public static void printSolution(Class<? extends EulerSolver> clazz) {
        EulerSolver eulerSolver = ClassFactory.getObjectOf(clazz);
        solve(eulerSolver);
    }

    private static void solve(EulerSolver eulerSolver) {
        long milis = System.currentTimeMillis();
        System.out.println(eulerSolver.solveToString());
        milis = System.currentTimeMillis()-milis;
        System.out.println("Solved in "+(milis/1000.0)+" seconds");
    }
}
