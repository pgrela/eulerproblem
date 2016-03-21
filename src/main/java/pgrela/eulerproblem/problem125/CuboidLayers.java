package pgrela.eulerproblem.problem125;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.Arrays;
import java.util.TreeSet;

import pgrela.eulerproblem.common.EulerSolver;

public class CuboidLayers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CuboidLayers.class);
    }

    public long solve() {
        Cuboid seed = new Cuboid(1, 1, 1);
        CuboidLayer seedLayer = new CuboidLayer(seed);
        TreeSet<CuboidLayer> queue = new TreeSet<>();
        queue.add(seedLayer);
        while (true) {
            CuboidLayer current = queue.pollFirst();
            if (current.getLayer() == 0) {
                for (Cuboid generated : current.getCuboid().expand()) {
                    queue.add(new CuboidLayer(generated));
                }
            }
            if (current.layerSize > 200) {
                return current.layerSize;
            }
            System.out.println(current.layerSize);

            current.bumpLayer();
            queue.add(current);
        }

    }

    private class Cuboid {
        long a, b, c;

        public Cuboid(long a, long b, long c) {
            long[] ints = {a, b, c};
            Arrays.sort(ints);
            this.a = ints[0];
            this.b = ints[1];
            this.c = ints[2];
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CuboidLayer that = (CuboidLayer) o;

            return cuboid.equals(that.cuboid);

        }

        @Override
        public int hashCode() {
            return cuboid != null ? cuboid.hashCode() : 0;
        }
    }

}
