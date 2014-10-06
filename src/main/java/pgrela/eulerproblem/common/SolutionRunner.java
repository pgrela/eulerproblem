package pgrela.eulerproblem.common;

public class SolutionRunner {
    public static void printSolution(Class<? extends EulerSolver> clazz) {
        EulerSolver eulerSolver = ClassFactory.getObjectOf(clazz);
        System.out.println(eulerSolver.solveToString());
    }
}
