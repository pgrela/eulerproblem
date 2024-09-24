package pgrela.eulerproblem.problem185;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.Arrays;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class NumberMind implements EulerSolver {
    private static String[] GUESESSES = new String[]{
            "5616185650518293 ;2 correct",
            "3847439647293047 ;1 correct",
            "5855462940810587 ;3 correct",
            "9742855507068353 ;3 correct",
            "4296849643607543 ;3 correct",
            "3174248439465858 ;1 correct",
            "4513559094146117 ;2 correct",
            "7890971548908067 ;3 correct",
            "8157356344118483 ;1 correct",
            "2615250744386899 ;2 correct",
            "8690095851526254 ;3 correct",
            "6375711915077050 ;1 correct",
            "6913859173121360 ;1 correct",
            "6442889055042768 ;2 correct",
            "2321386104303845 ;0 correct",
            "2326509471271448 ;2 correct",
            "5251583379644322 ;2 correct",
            "1748270476758276 ;3 correct",
            "4895722652190306 ;1 correct",
            "3041631117224635 ;3 correct",
            "1841236454324589 ;3 correct",
            "2659862637316867 ;2 correct"
    };
    private static String[] GUESESSES_SAMPLE = new String[]{
            "90342 ;2 correct",
            "70794 ;0 correct",
            "39458 ;2 correct",
            "34109 ;1 correct",
            "51545 ;2 correct",
            "12531 ;1 correct"
    };

    public static void main(String[] args) {
        printSolution(NumberMind.class);
    }

    @Override
    public String solveToString() {
        String[] sortedGuesses = Arrays.stream(GUESESSES)
                .map(StringBuilder::new)
                .map(StringBuilder::reverse)
                .map(StringBuilder::toString)
                .sorted()
                .map(StringBuilder::new)
                .map(StringBuilder::reverse)
                .map(StringBuilder::toString)
                .toArray(String[]::new);
        int[][] guesses = Arrays.stream(sortedGuesses).map(s -> s.split(";")[0]).map(String::trim).map(s -> s.chars().map(character -> character - '0').toArray()).toArray(int[][]::new);
        int[] corrects = Arrays.stream(sortedGuesses).map(s -> s.split(";")[1].substring(0, 1)).mapToInt(Integer::valueOf).toArray();

        return new SolutionGuesser(guesses, corrects).guessSolution().toCertainSolution();
    }


    static class SolutionGuesser {
        private final boolean[][] possibleDigits;
        private final int[] counters;
        private final boolean[][] c;
        private final boolean[][] c1;
        private final boolean[][] c2;
        private final boolean[][] c3;
        private final int[][] guesses;
        private final int[] corrects;
        private final int digits;

        SolutionGuesser(int[][] guesses, int[] corrects) {
            this.guesses = guesses;
            this.corrects = corrects;
            digits = guesses[0].length;
            counters = new int[digits];
            possibleDigits = new boolean[digits][10];
            for (int i = 0; i < digits; i++) {
                Arrays.fill(possibleDigits[i], true);
                counters[i] = 10;
            }
            c1 = new boolean[guesses.length][10];
            c2 = new boolean[guesses.length][10];
            c3 = new boolean[guesses.length][10];
            c = new boolean[guesses.length][digits];
        }

        SolutionGuesser guessSolution() {
            guessSolution(0);
            return this;
        }

        boolean guessSolution(int guess) {
            if (guess == guesses.length) {
                return true;
            }
            if (corrects[guess] == 0) {
                if (!canRemoveRange(guesses[guess], 0, digits)) return false;
                removeRange(guesses[guess], c[guess], 0, digits);
                if (guessSolution(guess + 1)) {
                    return true;
                }
                restoreRemoved(guesses[guess], c[guess]);
                return false;
            }
            if (corrects[guess] == 1) {
                return singleCertain(guess, 0);
            }
            if (corrects[guess] == 2) {
                return doubleCertain(guess, 0);
            }
            if (corrects[guess] == 3) {
                for (int first = 0; first < digits; first++) {
                    if (!canBeSetAsCertain(guesses[guess][first], first)) continue;
                    if (!canRemoveRange(guesses[guess], 0, first)) continue;
                    removeRange(guesses[guess], c[guess], 0, first);
                    setAsCertain(guesses[guess][first], first, c1[guess]);
                    if (doubleCertain(guess, first + 1))
                        return true;
                    restoreRemoved(first, c1[guess]);
                    restoreRemoved(guesses[guess], c[guess], 0, first);
                }
                return false;
            }
            throw new IllegalStateException();
        }

        private boolean doubleCertain(int guess, int start) {
            for (int position = start; position < digits; position++) {
                if (!this.canBeSetAsCertain(guesses[guess][position], position)) continue;
                if (!this.canRemoveRange(guesses[guess], start, position)) continue;
                this.removeRange(guesses[guess], c[guess], start, position);
                this.setAsCertain(guesses[guess][position], position, c2[guess]);
                if (this.singleCertain(guess, position + 1))
                    return true;
                this.restoreRemoved(guesses[guess], c[guess], start, position);
                this.restoreRemoved(position, c2[guess]);
            }
            return false;
        }

        private boolean singleCertain(int guess, int start) {
            for (int position = start; position < digits; position++) {
                if (!this.canBeSetAsCertain(guesses[guess][position], position)) continue;
                if (!this.canRemoveRange(guesses[guess], start, position)) continue;
                if (!this.canRemoveRange(guesses[guess], position + 1, digits)) continue;
                
                this.removeRange(guesses[guess], c[guess], start, position);
                this.setAsCertain(guesses[guess][position], position, c3[guess]);
                this.removeRange(guesses[guess], c[guess], position + 1, digits);
                if (guessSolution(guess + 1)) {
                    return true;
                }
                this.restoreRemoved(guesses[guess], c[guess], position + 1, digits);
                this.restoreRemoved(guesses[guess], c[guess], start, position);
                this.restoreRemoved(position, c3[guess]);
            }
            return false;
        }

        boolean canBeSetAsCertain(int digit, int position) {
            return possibleDigits[position][digit];
        }

        boolean canBeSetAsRemoved(int digit, int position) {
            return !possibleDigits[position][digit] || counters[position] > 1;
        }

        boolean setAsRemoved(int digit, int position) {
            if (possibleDigits[position][digit]) {
                possibleDigits[position][digit] = false;
                --counters[position];
                return true;
            }
            return false;
        }

        void setAsPossible(int digit, int position) {
            possibleDigits[position][digit] = true;
            ++counters[position];
        }

        boolean canRemoveRange(int[] guess, int start, int endExclusive) {
            for (int position = start; position < endExclusive; position++)
                if (!canBeSetAsRemoved(guess[position], position)) return false;
            return true;
        }

        void removeRange(int[] guess, boolean[] memory, int start, int endExclusive) {
            for (int position = start; position < endExclusive; position++)
                memory[position] = setAsRemoved(guess[position], position);
        }

        void setAsCertain(int digit, int position, boolean[] memory) {
            for (int otherDigit = 0; otherDigit < 10; otherDigit++) {
                memory[otherDigit] = otherDigit != digit && setAsRemoved(otherDigit, position);
            }
        }

        void restoreRemoved(int[] guess, boolean[] setAsRemoved) {
            restoreRemoved(guess, setAsRemoved, 0, possibleDigits.length);
        }

        void restoreRemoved(int[] guess, boolean[] setAsRemoved, int start, int endExclusive) {
            for (int position = start; position < endExclusive; position++) {
                if (setAsRemoved[position]) setAsPossible(guess[position], position);
            }
        }

        void restoreRemoved(int position, boolean[] removedDigits) {
            for (int digit = 0; digit < 10; digit++) {
                if (removedDigits[digit]) setAsPossible(digit, position);
            }
        }

        String toCertainSolution() {
            StringBuilder stringBuilder = new StringBuilder();
            for (boolean[] possibleDigit : possibleDigits) {
                for (int digit = 0; digit < 10; digit++) {
                    if (possibleDigit[digit]) {
                        stringBuilder.append(digit);
                    }
                }
            }
            return stringBuilder.toString();
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (boolean[] possibleDigit : possibleDigits) {
                for (int digit = 0; digit < 10; digit++) {
                    if (possibleDigit[digit]) {
                        stringBuilder.append(digit);
                    } else {
                        stringBuilder.append(" ");
                    }
                }
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
    }
}

