package pgrela.eulerproblem.problem107;

import pgrela.eulerproblem.common.Coordinates;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Files;

import java.util.BitSet;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class MinimalNetwork implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem107/network.txt";

    public static void main(String[] args) {
        printSolution(MinimalNetwork.class);
    }

    public long solve() {
        int[][] matrix = Files.fileTo2DArray(",", RESOURCE_FILE, "-");
        BitSet spanningTree = new BitSet();
        spanningTree.set(0);
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        IntStream.range(0, 40)
                .filter(i -> matrix[0][i] > 0).forEach(i -> queue.add(new Edge(i, matrix[0][i])));
        int sum = 0;
        while (spanningTree.cardinality() != 40) {
            Edge nextEdge = queue.poll();
            if (!spanningTree.get(nextEdge.otherNode)) {
                sum += nextEdge.weight;
                spanningTree.set(nextEdge.otherNode);
                IntStream.range(0, 40)
                        .filter(i -> matrix[nextEdge.otherNode][i] > 0)
                        .filter(i -> !spanningTree.get(i))
                        .forEach(i -> queue.add(new Edge(i, matrix[nextEdge.otherNode][i])));
            }
        }
        return Coordinates.square(0, 0, 39, 39).filter(p -> p.x > p.y).mapToInt(p -> matrix[p.x][p.y]).sum() - sum;

    }

    private class Edge implements Comparable {
        int otherNode;
        int weight;

        public Edge(int otherNode, int weight) {
            this.otherNode = otherNode;
            this.weight = weight;
        }

        @Override
        public int compareTo(Object o) {
            return weight - ((Edge) o).weight;
        }

        @Override
        public String toString() {
            return "Edge{otherNode=" + otherNode + ", weight=" + weight + '}';
        }
    }


}
