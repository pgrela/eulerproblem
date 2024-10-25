package pgrela.eulerproblem.problem215;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CrackFreeWalls implements EulerSolver {


    private static final int WIDTH = 32;
    private static final int LAYERS = 10;

    public static void main(String[] args) {
        printSolution(CrackFreeWalls.class);
    }

    @Override
    public long solve() {
        Map<Long, Long> underlyingCombinations = buildUp(new ArrayList<>(), 1, WIDTH)
                .stream()
                .collect(Collectors.toMap(Function.identity(), i -> 1L));

        for (int height = 1; height < LAYERS; height++) {
            Map<Long, Long> nextLayerCombinations = new HashMap<>();
            for (Long nextLayer : underlyingCombinations.keySet()) {
                nextLayerCombinations.put(nextLayer, underlyingCombinations.keySet().stream()
                        .filter(belowLayer -> (nextLayer & belowLayer) == ((1L << WIDTH) | 1))
                        .mapToLong(underlyingCombinations::get).sum());
            }
            underlyingCombinations = nextLayerCombinations;
        }
        return underlyingCombinations.values().stream().mapToLong(n -> n).sum();
    }

    private ArrayList<Long> buildUp(ArrayList<Long> layers, long alreadyBuilt, int leftSpace) {
        if (leftSpace == 0) {
            layers.add(alreadyBuilt);
        }
        for (int brick = 2; brick <= 3; brick++) {
            long nextLayer = alreadyBuilt;
            if (leftSpace >= brick) {
                nextLayer <<= brick;
                nextLayer |= 1;
                buildUp(layers, nextLayer, leftSpace - brick);
            }
        }
        return layers;
    }
}

