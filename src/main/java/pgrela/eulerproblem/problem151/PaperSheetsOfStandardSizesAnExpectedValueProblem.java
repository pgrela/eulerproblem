package pgrela.eulerproblem.problem151;

import com.google.common.collect.Lists;
import pgrela.eulerproblem.common.EulerSolver;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PaperSheetsOfStandardSizesAnExpectedValueProblem implements EulerSolver {
    private Map<String, Double> cache = new HashMap<>();

    public static void main(String[] args) throws ParseException {
        printSolution(PaperSheetsOfStandardSizesAnExpectedValueProblem.class);
    }

    public String solveToString() {
        Collection<Integer> envelope = Lists.newArrayList(1);
        return String.format("%.6f", takeOneSheet(envelope) - 2).replaceAll(",", ".");
    }

    private double takeOneSheet(Collection<Integer> envelope) {
        if (cache.containsKey(envelope.toString())) return cache.get(envelope.toString());
        double times = envelope.size() == 1 ? 1 : 0;
        for (int sheet : envelope) {
            Collection<Integer> newEnvelope = new ArrayList<>(envelope);
            newEnvelope.remove(sheet);
            IntStream.rangeClosed(sheet + 1, 5).forEach(newEnvelope::add);
            times += takeOneSheet(newEnvelope) / envelope.size();
        }
        cache.put(envelope.toString(), times);
        return times;
    }
}

