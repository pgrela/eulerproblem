package pgrela.eulerproblem.problem168;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.text.ParseException;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class NumberRotations implements EulerSolver {

    public static void main(String[] args) throws ParseException {
        printSolution(NumberRotations.class);
    }


    public long solve() {
        long s = 0;
        for (int lastDigit = 0; lastDigit < 10; lastDigit++) {
            for (int multiplier = 1; multiplier < 10; multiplier++) {
                StringBuilder stringBuilder = new StringBuilder(String.valueOf(lastDigit));
                int lastProcessed = lastDigit;
                int remainder = 0;
                for (int digits = 0; digits < 100; digits++) {
                    int nextDigit = lastProcessed * multiplier + remainder;
                    remainder = nextDigit / 10;
                    lastProcessed = nextDigit % 10;
                    if (lastProcessed == lastDigit && remainder == 0 && stringBuilder.charAt(0) != '0' && stringBuilder.length() > 1) {
                        s += Long.valueOf(stringBuilder.substring(Math.max(0, stringBuilder.length() - 5)));
                    }
                    stringBuilder.insert(0, lastProcessed);
                }
            }
        }
        return s % Maths.pow(10, 5);
    }
}

