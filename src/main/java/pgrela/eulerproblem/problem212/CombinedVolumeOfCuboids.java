package pgrela.eulerproblem.problem212;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Triple;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CombinedVolumeOfCuboids implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CombinedVolumeOfCuboids.class);
    }

    private static int[] s = null;

    @Override
    public long solve() {
        PriorityQueue<Triple<Integer, Integer, Cuboid>> xEvents = new PriorityQueue<>(100_000, Comparator.comparing(Triple::first));
        IntStream.range(0, 50_000).mapToObj(n -> Cuboid.axisAlignedCuboid(
                s(6 * n) % 10_000,
                s(6 * n + 1) % 10_000,
                s(6 * n + 2) % 10_000,
                1 + s(6 * n + 3) % 399,
                1 + s(6 * n + 4) % 399,
                1 + s(6 * n + 5) % 399
        )).flatMap(cuboid -> Stream.of(Triple.triple(cuboid.getX1(), 1, cuboid), Triple.triple(cuboid.getX2(), -1, cuboid))).forEach(xEvents::add);

        Node root = InnerNode.createTree(10_398);
        int lastX = xEvents.peek().third().getX1();
        TreeSet<Triple<Integer, Integer, Cuboid>> yEvents = new TreeSet<>(Comparator.<Triple<Integer, Integer, Cuboid>, Integer>comparing(Triple::first).thenComparingInt(t -> t.third().hashCode()));
        long volume = 0;
        while (!xEvents.isEmpty()) {
            Triple<Integer, Integer, Cuboid> xEvent = xEvents.poll();
            if (xEvent.first() != lastX) {
                if (!yEvents.isEmpty()) {
                    long area = 0;
                    long lastY = yEvents.first().third().getY1();
                    for (Triple<Integer, Integer, Cuboid> yEvent : yEvents) {
                        if (lastY != yEvent.first()) {
                            area += root.getCoveredArea() * (yEvent.first() - lastY);
                            lastY = yEvent.first();
                        }
                        Cuboid yCuboid = yEvent.third();
                        if (yEvent.second() == 1) {
                            root.addLayer(yCuboid.getZ1(), yCuboid.getZ2());
                        } else {
                            root.removeLayer(yCuboid.getZ1(), yCuboid.getZ2());
                        }
                    }
                    volume += area * (xEvent.first() - lastX);
                }
                lastX = xEvent.first();
            }
            Cuboid xCuboid = xEvent.third();
            if (xEvent.second() == 1) {
                yEvents.add(Triple.triple(xCuboid.getY1(), 1, xCuboid));
                yEvents.add(Triple.triple(xCuboid.getY2(), -1, xCuboid));
            }
            if (xEvent.second() == -1) {
                yEvents.remove(Triple.triple(xCuboid.getY1(), 1, xCuboid));
                yEvents.remove(Triple.triple(xCuboid.getY2(), -1, xCuboid));
            }
        }

        return volume;
    }

    private static int s(int n) {
        if (s == null) {
            s = new int[300_000];
            IntStream.range(0, 55).forEach(i -> s[i] = (100003 - (200003 * (i + 1)) % 1000000 + 1000000 + ((((300007 * (i + 1)) % 1000000) % 1000000) * (i + 1) % 1000000) * (i + 1)) % 1000000);
            IntStream.range(55, s.length).forEach(i -> s[i] = (s[i - 24] + s[i - 55]) % 1000000);
        }
        return s[n];
    }

}

class Cuboid {
    private final int x1, y1, z1, x2, y2, z2;


    Cuboid(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getZ1() {
        return z1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getZ2() {
        return z2;
    }

    public static Cuboid axisAlignedCuboid(int x0, int y0, int z0, int dx, int dy, int dz) {
        return new Cuboid(x0, y0, z0, x0 + dx, y0 + dy, z0 + dz);
    }

    @Override
    public String toString() {
        return "Cuboid{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", z1=" + z1 +
                ", dx=" + (x2 - x1) +
                ", dy=" + (y2 - y1) +
                ", dz=" + (z2 - z1) +
                '}';
    }
}

