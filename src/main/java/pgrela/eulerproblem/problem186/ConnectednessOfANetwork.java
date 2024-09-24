package pgrela.eulerproblem.problem186;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ConnectednessOfANetwork implements EulerSolver {

    private List<Integer> s = new ArrayList<>(Collections.singletonList(0));

    public static void main(String[] args) {
        printSolution(ConnectednessOfANetwork.class);
    }

    @Override
    public long solve() {
        int[] parents = IntStream.range(0, ONE_MILLION).toArray();
        int[] size = IntStream.range(0, ONE_MILLION).map(i -> 1).toArray();
        int call = 1;
        int successfulCalls = 0;
        while (size[parents[root(parents, 524287)]] < ONE_MILLION / 100 * 99) {
            int caller = s(2 * call);
            int called = s(2 * call - 1);
            ++call;
            if (called == caller) continue;
            ++successfulCalls;
            int callerRoot = root(parents, caller);
            int calledRoot = root(parents, called);
            if (callerRoot != calledRoot) {
                parents[callerRoot] = parents[calledRoot];
                size[calledRoot] += size[callerRoot];
            }

        }
        return successfulCalls;
    }

    int root(int[] parents, int user) {
        if (parents[user] != user) {
            parents[user] = root(parents, parents[user]);
        }
        return parents[user];
    }

    int s(int k) {
        if (k < s.size()) return s.get(k);
        while (s.size() != k) {
            s(s.size());
        }
        int value;
        if (k <= 55) {
            value = (int) ((100003L - 200003L * k + 300007L * k * k * k) % ONE_MILLION);
        } else {
            value = (s(k - 24) + s(k - 55)) % ONE_MILLION;
        }
        s.add(value);
        return value;
    }
}

