package pgrela.eulerproblem.problem162;

import pgrela.eulerproblem.common.EulerSolver;

import java.text.ParseException;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class HexadecimalNumbers implements EulerSolver {

    public static final int DIGITS = 16;

    public static void main(String[] args) throws ParseException {
        printSolution(HexadecimalNumbers.class);
    }

    public String solveToString() {
        S[] cache = IntStream.rangeClosed(0, DIGITS).mapToObj(i -> new S()).toArray(S[]::new);
        cache[1].OUT01A = 13;
        cache[1].OUT1A_WITH0 = cache[1].OUT0A_WITH1 = cache[1].OUT01_WITHA = 1;
        for (int i = 2; i <= DIGITS; i++) {
            cache[i].WITH01A = cache[i - 1].WITH01A * 16 + cache[i - 1].OUT1_WITH0A + cache[i - 1].OUTA_WITH01 + cache[i - 1].OUT0_WITH1A;
            cache[i].OUT1_WITH0A = cache[i - 1].OUT1_WITH0A * 15 + cache[i - 1].OUT1A_WITH0 + cache[i - 1].OUT01_WITHA;
            cache[i].OUTA_WITH01 = cache[i - 1].OUTA_WITH01 * 15 + cache[i - 1].OUT1A_WITH0 + cache[i - 1].OUT0A_WITH1;
            cache[i].OUT0_WITH1A = cache[i - 1].OUT0_WITH1A * 15 + cache[i - 1].OUT01_WITHA + cache[i - 1].OUT0A_WITH1;
            cache[i].OUT01_WITHA = cache[i - 1].OUT01_WITHA * 14 + cache[i - 1].OUT01A;
            cache[i].OUT0A_WITH1 = cache[i - 1].OUT0A_WITH1 * 14 + cache[i - 1].OUT01A;
            cache[i].OUT1A_WITH0 = cache[i - 1].OUT1A_WITH0 * 14 + cache[i - 1].OUT01A;
            cache[i].OUT01A = cache[i - 1].OUT01A * 13;
        }
        return Long.toHexString(cache[DIGITS].WITH01A - IntStream.range(0, DIGITS).mapToLong(i -> cache[i].OUT0_WITH1A).sum()).toUpperCase();
    }

    static class S {
        long OUT01A = 0, OUT1A_WITH0 = 0, OUT0A_WITH1 = 0, OUT01_WITHA = 0, OUT0_WITH1A = 0, OUT1_WITH0A = 0, OUTA_WITH01 = 0, WITH01A = 0;
    }
}

