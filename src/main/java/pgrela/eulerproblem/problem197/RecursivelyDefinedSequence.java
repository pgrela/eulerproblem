package pgrela.eulerproblem.problem197;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RecursivelyDefinedSequence implements EulerSolver {

    public static void main(String[] args) {
        printSolution(RecursivelyDefinedSequence.class);
    }

    @Override
    public String solveToString() {
        long i=520;
        double currentU=-1;
        while(true){
            currentU=f(currentU);
            if(--i==0){
                return String.valueOf(currentU+f(currentU));
            }
        }
    }

    public double f(double x) {
        return Math.floor(Math.pow(2, 30.403243784d - x * x)) / 1e9d;
    }
}

