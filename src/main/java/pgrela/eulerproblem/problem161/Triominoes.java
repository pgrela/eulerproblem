package pgrela.eulerproblem.problem161;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.LongPair;
import pgrela.eulerproblem.common.Point;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class Triominoes implements EulerSolver {
    private static final int WIDTH = 9;
    private static final int HEIGHT = 12;

    private Map<LongPair, Long> cache = new HashMap<>();

    public static void main(String[] args) throws ParseException {
        printSolution(Triominoes.class);
    }

    enum Triomino {
        L3(0, 1, 1, Point.point(0, 1), Point.point(-1, 1)),
        L1(1, 1, 0, Point.point(0, 1), Point.point(1, 0)),
        L2(1, 1, 0, Point.point(0, 1), Point.point(1, 1)),
        L4(1, 1, 0, Point.point(1, 0), Point.point(1, 1)),
        T1(0, 2, 0, Point.point(0, 1), Point.point(0, 2)),
        T2(2, 0, 0, Point.point(1, 0), Point.point(2, 0));

        private final int exapnsionRight;
        private final int expansionBottom;
        private final int expansionLeft;
        private final Point[] points;

        Triomino(int exapnsionRight, int expansionBottom, int expansionLeft, Point... points) {
            this.exapnsionRight = exapnsionRight;
            this.expansionBottom = expansionBottom;
            this.expansionLeft = expansionLeft;
            this.points = points;
        }
    }

    public long solve() {
        return fills(new Board(), 0, 0);
    }

    private long fills(Board board, int x, int y) {
        if (x >= WIDTH) return fills(board, x % WIDTH, y + 1);
        if (y >= HEIGHT) return 1;
        if (!board.isEmpty(x, y)) return fills(board, x + 1, y);
        long s = 0;
        LongPair hash = board.hash();
        if (cache.containsKey(hash)) {
            return cache.get(hash);
        }
        for (Triomino triomino : Triomino.values()) {
            if (board.put(x, y, triomino)) {
                s += fills(board, x + 1, y);
                board.remove(x, y, triomino);
            }

        }
        cache.put(hash, s);
        return s;
    }

    static class Board {
        static final int SIX = 6;
        static final int ROW = 9;
        long top = 0, bottom = 0;

        boolean isEmpty(int x, int y) {
            if (y < SIX) return isEmpty(top, x, y);
            return isEmpty(bottom, x, y-SIX);
        }

        private boolean isEmpty(long vector, int x, int y) {
            return ((vector >> (y * ROW + x)) & 1) == 0;
        }

        public boolean put(int x, int y, Triomino triomino) {
            if (x + triomino.exapnsionRight >= WIDTH) return false;
            if (x - triomino.expansionLeft < 0) return false;
            if (y + triomino.expansionBottom >= HEIGHT) return false;
            for (Point p : triomino.points) {
                if (!isEmpty(x + p.x, y + p.y)) return false;
            }
            putAt(x, y);
            for (Point p : triomino.points) {
                putAt(x + p.x, y + p.y);
            }
            return true;
        }

        private void putAt(int x, int y) {
            if (!isEmpty(x, y)) {
                throw new RuntimeException();
            }
            if (y < SIX) {
                top |= 1L << (y * ROW + x);
            } else {
                y -= SIX;
                bottom |= 1L << (y * ROW + x);
            }
        }

        void remove(int x, int y, Triomino triomino) {
            for (Point p : triomino.points) {
                removeAt(x + p.x, y + p.y);
            }
            removeAt(x, y);
        }

        private void removeAt(int x, int y) {
            if (isEmpty(x, y)) {
                throw new RuntimeException();
            }
            if (y < SIX) {
                top ^= 1L << (y * ROW + x);
            } else {
                y -= SIX;
                bottom ^= 1L << (y * ROW + x);
            }
        }

        LongPair hash() {
            return LongPair.pair(top, bottom);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < SIX*2; i++) {
                for (int j = 0; j < ROW; j++) {
                    stringBuilder.append(isEmpty(j, i) ? "." : "#");
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append("\n");
            return stringBuilder.toString();
        }
    }
}

