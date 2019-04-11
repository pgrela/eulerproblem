package pgrela.eulerproblem.problem167;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class InvestigatingUlamSequences implements EulerSolver {

    private static final int BUFFER_SIZE = 2000;
    private static final int MAX_BACK_TRACK = 35;
    private static final int SEQUENCE_MINIMUM_LENGTH = 10;
    private static long K = Maths.pow(10, 11L);

    public static void main(String[] args) throws ParseException {
        printSolution(InvestigatingUlamSequences.class);
    }

    static class Poller {
        int[] buffer = new int[BUFFER_SIZE];
        int current = 0;
        int[] firstElements = new int[MAX_BACK_TRACK];
        int nextFirstElement = 0;

        Poller(int secondValue) {
            buffer[2] = 1;
            buffer[secondValue] = 1;
        }

        int next() {
            while (true) {
                int indexInBuffer = current % BUFFER_SIZE;
                if (buffer[indexInBuffer] == 1) {
                    for (int j = 0; j < nextFirstElement; j++) {
                        buffer[(firstElements[j] + current) % BUFFER_SIZE]++;
                    }
                    if (nextFirstElement < firstElements.length) {
                        firstElements[nextFirstElement++] = current;
                    }
                    buffer[indexInBuffer] = 0;
                    return current++;
                }
                buffer[indexInBuffer] = 0;
                ++current;
            }
        }
    }

    static class Discovered {
        Poller poller;
        List<Integer> us = new ArrayList<>();

        Discovered(Poller poller) {
            this.poller = poller;
        }

        int get(int i) {
            while (us.size() <= i) {
                us.add(poller.next());
            }
            return us.get(i);
        }

        int getDiff(int i) {
            return get(i + 1) - get(i);
        }
    }

    public long solve() {
        long sum = 0;
        UlamSequence:
        for (int n = 2; n <= 10; n++) {
            Discovered discovered = new Discovered(new Poller(2 * n + 1));
            int sequenceStart = 0;
            SequenceStarterLoop:
            while (true) {
                for (int i = sequenceStart; i < sequenceStart + SEQUENCE_MINIMUM_LENGTH; i++) {
                    if (discovered.getDiff(i) % 2 == 1) {
                        sequenceStart = i + 1;
                        continue SequenceStarterLoop;
                    }
                }
                for (int secondRepetition = sequenceStart + SEQUENCE_MINIMUM_LENGTH; true; secondRepetition++) {
                    int maybeLength = secondRepetition - sequenceStart;
                    boolean isHit = true;
                    for (int i = 0; i < maybeLength; i++) {
                        if (discovered.getDiff(secondRepetition + i) % 2 == 1) {
                            sequenceStart = secondRepetition + i + 1;
                            continue SequenceStarterLoop;
                        }
                        if (discovered.getDiff(sequenceStart + i) != discovered.getDiff(secondRepetition + i)) {
                            isHit = false;
                            break;
                        }
                    }
                    if (isHit) {
                        int diff = discovered.get(sequenceStart + maybeLength) - discovered.get(sequenceStart);
                        long skips = (K - 1 - sequenceStart) / maybeLength;
                        int left = (int) (K - 1 - sequenceStart - maybeLength * skips);
                        sum += discovered.get(sequenceStart) + diff * skips + (discovered.get(sequenceStart + left) - discovered.get(sequenceStart));
                        continue UlamSequence;
                    }
                }
            }
        }
        return sum;
    }
}

