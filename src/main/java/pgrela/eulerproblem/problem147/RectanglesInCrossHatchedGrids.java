package pgrela.eulerproblem.problem147;

import pgrela.eulerproblem.common.EulerSolver;

import java.text.ParseException;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RectanglesInCrossHatchedGrids implements EulerSolver {

    public static void main(String[] args) throws ParseException {
        printSolution(RectanglesInCrossHatchedGrids.class);
    }

    public long solve() {
        long r = 0;
        for (int width = 1; width <= 47; width++) {
            for (int height = 1; height <= 43; height++) {

                {
                    boolean[][] h = new boolean[height][width];
                    for (int i = 0; i < h.length; i++) {
                        for (int j = 0; j < h[i].length; j++) {
                            h[i][j] = true;
                        }
                    }
                    r += count(h);
                }
                {
                    boolean[][] h = new boolean[height + width - 2][height + width - 2];
                    for (int i = 0; i < h.length; i++) {
                        for (int j = 0; j < h[i].length; j++) {
                            h[i][j] = true;
                        }
                    }
                    for (int i = 0; i < width - 2; i++) {
                        for (int j = 0; j < width - 2 - i; j++) {
                            h[i][j] = false;
                            h[h.length - 1 - i][h[0].length - 1 - j] = false;
                        }
                    }
                    for (int i = 0; i < height - 2; i++) {
                        for (int j = 0; j < height - 2 - i; j++) {
                            h[i][h[0].length - 1 - j] = false;
                            h[h.length - 1 - i][j] = false;
                        }
                    }
                    r += count(h);
                }
            }
        }
        return r;
    }

    private long count(boolean[][] h) {
        long r = 0;
        for (int i = 0; i < h.length; i++) {
            for (int j = 0; j < h[i].length; j++) {
                if (!h[i][j]) continue;
                int d = 0;
                int stretch = Integer.MAX_VALUE;
                while (i + d < h.length) {
                    int w = 0;
                    while (j + w < h[i].length && w < stretch && h[i + d][j + w]) {
                        ++r;
                        ++w;
                    }
                    stretch = w;
                    ++d;
                }

            }
        }
        return r;
    }


}

