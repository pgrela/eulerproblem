package pgrela.eulerproblem.problem166;

import pgrela.eulerproblem.common.EulerSolver;

import java.text.ParseException;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CrissCross implements EulerSolver {


    public static void main(String[] args) throws ParseException {
        printSolution(CrissCross.class);
    }

    public long solve() {
        long c = 0;
        for (int firstRow = 0; firstRow < 10000; firstRow++) {
            int a00 = firstRow / 1000;
            int a10 = firstRow / 100 % 10;
            int a20 = firstRow / 10 % 10;
            int a30 = firstRow % 10;
            int sum = a00 + a10 + a20 + a30; // first row
            for (int firstColumn = 0; firstColumn < 100; firstColumn++) {
                int a01 = firstColumn / 10;
                int a02 = firstColumn % 10;
                if (a01 + a02 + a00 <= sum) { // first column
                    int a03 = sum - a00 - a01 - a02;
                    if (10 > a03 && a03 >= 0) {
                        for (int a12 = 0; a12 < 10; a12++) {
                            int a21 = sum - a30 - a12 - a03;
                            if (10 > a21 && a21 >= 0) { // first diagonal
                                for (int a11 = 0; a11 < 10; a11++) {
                                    int a31 = sum - a01 - a11 - a21;
                                    if (10 > a31 && a31 >= 0) { // second row
                                        for (int a22 = 0; a22 < 10; a22++) {
                                            int a32 = sum - a22 - a12 - a02;
                                            if (10 > a32 && a32 >= 0) { // third row
                                                int a13 = sum - a12 - a11 - a10;
                                                int a23 = sum - a22 - a21 - a20;
                                                int a33 = sum - a32 - a31 - a30;
                                                if (
                                                        a03 + a13 + a23 + a33 == sum // forth row
                                                                && 10 > a13 && a13 >= 0 // second column
                                                                && 10 > a23 && a23 >= 0 // third column
                                                                && 10 > a33 && a33 >= 0 // forth column
                                                                && a00 + a11 + a22 + a33 == sum //second diagonal
                                                        ) {
                                                    ++c;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return c;
    }
}

