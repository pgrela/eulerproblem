package pgrela.eulerproblem.problem126;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.*;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CuboidLayers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CuboidLayers.class);
    }

    public long solve() {
        Cuboid seed = new Cuboid(1, 1, 1);
        PriorityQueue<CuboidLayer> queue = new PriorityQueue<>();
        queue.add(new CuboidLayer(seed));
        Set<Cuboid> cuboids = new HashSet<>();
        cuboids.add(seed);

        while (true) {
            long top = queue.peek().getLayerSize();
            int peeked = 0;
            while (queue.peek().getLayerSize() == top) {
                takeIt(queue, cuboids);
                ++peeked;
            }
            if (peeked == 1000) {
                return top;
            }
        }
    }

    private void takeIt(PriorityQueue<CuboidLayer> queue, Set<Cuboid> cuboids) {
        CuboidLayer current = queue.poll();
        if (current.getLayer() == 0) {
            for (Cuboid generated : current.getCuboid().expand()) {
                if (cuboids.add(generated)) {
                    queue.add(new CuboidLayer(generated));
                }
            }
        }

        current.bumpLayer();
        queue.add(current);
    }

    private class Cuboid {
        long a, b, c;

        public Cuboid(long a, long b, long c) {
            long[] dimensions = {a, b, c};
            Arrays.sort(dimensions);
            this.a = dimensions[0];
            this.b = dimensions[1];
            this.c = dimensions[2];
        }

        public long area() {
            return 2 * (a * (b + c) + b * c);
        }

        public long edgesLength() {
            return 4 * (a + b + c);
        }

        public Cuboid[] expand() {
            return new Cuboid[]{new Cuboid(a + 1, b, c), new Cuboid(a, b + 1, c), new Cuboid(a, b, c + 1)};
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cuboid cuboid = (Cuboid) o;

            return a == cuboid.a && b == cuboid.b && c == cuboid.c;

        }

        @Override
        public int hashCode() {
            int result = (int) (a ^ (a >>> 32));
            result = 31 * result + (int) (b ^ (b >>> 32));
            result = 31 * result + (int) (c ^ (c >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "Cuboid{" + "a=" + a + ", b=" + b + ", c=" + c + '}';
        }
    }

    private class CuboidLayer implements Comparable {
        Cuboid cuboid;
        int layer;
        long layerSize;

        public CuboidLayer(Cuboid cuboid) {
            this.cuboid = cuboid;
            this.layer = 0;
            this.layerSize = calculateLayerSize(cuboid, layer);
        }

        public long calculateLayerSize(Cuboid cuboid, int layer) {
            return cuboid.area() + layer * cuboid.edgesLength()
                    + 8 * (layer > 1 ? layer * (layer - 1) / 2 : 0);
        }

        public void bumpLayer() {
            ++this.layer;
            this.layerSize = calculateLayerSize(cuboid, layer);
        }

        public Cuboid getCuboid() {
            return cuboid;
        }

        public int getLayer() {
            return layer;
        }

        public long getLayerSize() {
            return layerSize;
        }

        @Override
        public int compareTo(Object o) {
            CuboidLayer other = (CuboidLayer) o;
            return (int) (layerSize - other.layerSize);
        }

        @Override
        public String toString() {
            return "CuboidLayer{" + "cuboid=" + cuboid + ", layer=" + layer + ", layerSize=" + layerSize + '}';
        }
    }

}
