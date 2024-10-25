package pgrela.eulerproblem.problem199;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class IterativeCirclePacking implements EulerSolver {

    public static final double BASE_CIRCLE_RADIUS = 1;
    public static final double FIRST_LEVEL_CIRCLE = BASE_CIRCLE_RADIUS / (2 * Math.sqrt(3) / 3 + 1);
    public static final int LEVELS = 10;

    public static void main(String[] args) {
        printSolution(IterativeCirclePacking.class);
    }

    @Override
    public String solveToString() {
        double area = 3 * circleArea(FIRST_LEVEL_CIRCLE)
                + areaWithinThreeCircles(LEVELS, FIRST_LEVEL_CIRCLE, FIRST_LEVEL_CIRCLE, FIRST_LEVEL_CIRCLE)
                + 3 * areaAroundBig(LEVELS, FIRST_LEVEL_CIRCLE, FIRST_LEVEL_CIRCLE);

        return String.format("%.8f", 1 - area / circleArea(BASE_CIRCLE_RADIUS));
    }

    private double areaWithinThreeCircles(int levels, double r1, double r2, double r3) {
        if (levels == 0) return 0;
        double r = fourthRadius(r1, r2, r3);
        --levels;
        return circleArea(r)
                + areaWithinThreeCircles(levels, r, r2, r3)
                + areaWithinThreeCircles(levels, r1, r, r3)
                + areaWithinThreeCircles(levels, r1, r2, r);
    }

    private double areaAroundBig(int levels, double r1, double r2) {
        if (levels == 0) return 0;
        double r = thirdRadius(r1, r2);
        --levels;
        return circleArea(r) + areaAroundBig(levels, r, r2) + areaAroundBig(levels, r1, r) + areaWithinThreeCircles(levels, r1, r2, r);
    }


    double fourthRadius(double r1, double r2, double r3) {
        return (r1 * r2 * r3) / (r1 * r2 + r2 * r3 + r1 * r3 + 2 * Math.sqrt(r1 * r2 * r3 * (r1 + r2 + r3)));
    }

    double thirdRadius(double r1, double r2) {
        return (r1 * r2 * -BASE_CIRCLE_RADIUS)
                / (r1 * r2 + r2 * -BASE_CIRCLE_RADIUS + r1 * -BASE_CIRCLE_RADIUS
                - 2 * Math.sqrt(r1 * r2 * -BASE_CIRCLE_RADIUS * (r1 + r2 - BASE_CIRCLE_RADIUS)));
    }

    private double circleArea(double r) {
        return Math.PI * r * r;
    }

}

