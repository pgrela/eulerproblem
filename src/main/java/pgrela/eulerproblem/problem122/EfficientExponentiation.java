package pgrela.eulerproblem.problem122;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.*;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class EfficientExponentiation implements EulerSolver {

    public static void main(String[] args) {
        printSolution(EfficientExponentiation.class);
    }

    public long solve() {
        //To prove: two non-optimal multiplications can result in an optimal one?
        int size = 200;
        List<Set<BitSet>> b = new ArrayList<>();
        IntStream.range(0, size + 1).forEach(i -> b.add(new HashSet<>()));
        BitSet bitSet = new BitSet();
        bitSet.set(0);
        bitSet.set(1);
        b.get(0).add(bitSet);
        b.get(1).add(bitSet);
        BitSet sumNewAndSelf = new BitSet();
        for (int currentE = 2; currentE <= size; currentE++) {
            int currentMin = 1000000;
            for (int oneSeed = 1; oneSeed < currentE; oneSeed++) {
                int otherSeed = currentE - oneSeed;
                if (otherSeed < oneSeed) continue;
                for (BitSet oneBitSet : b.get(oneSeed)) {
                    for (BitSet otherBitSet : b.get(otherSeed)) {
                        BitSet newBitSet = new BitSet();
                        newBitSet.set(currentE);
                        newBitSet.or(oneBitSet);
                        newBitSet.or(otherBitSet);
                        if(newBitSet.cardinality()>currentMin)continue;
                        boolean isNewSet = true;
                        for (Iterator<BitSet> iterator = b.get(currentE).iterator(); iterator.hasNext(); ) {
                            BitSet selfBitSet = iterator.next();
                            sumNewAndSelf.clear();
                            sumNewAndSelf.or(newBitSet);
                            sumNewAndSelf.or(selfBitSet);
                            if (sumNewAndSelf.cardinality() == selfBitSet.cardinality()) {
                                iterator.remove();
                                continue;
                            }
                            if (sumNewAndSelf.cardinality() == newBitSet.cardinality()) {
                                isNewSet = false;
                                break;
                            }
                        }
                        if (isNewSet) {
                            b.get(currentE).add(newBitSet);
                            currentMin = Math.min(currentE, newBitSet.cardinality());
                        }
                    }
                }
            }
            int min = b.get(currentE).stream().mapToInt(BitSet::cardinality).min().getAsInt();
            for (Iterator<BitSet> iterator = b.get(currentE).iterator(); iterator.hasNext(); ) {
                BitSet self = iterator.next();
                if(self.cardinality()>min)
                iterator.remove();
            }
        }

        //IntStream.rangeClosed(0, size).forEach(i -> System.out.println(i + " -> " + (b.get(i).stream().mapToInt(BitSet::cardinality).min().getAsInt() - 2)));
        return b.stream().mapToInt(s -> s.stream().mapToInt(BitSet::cardinality).min().getAsInt() - 2).sum();
    }
}
