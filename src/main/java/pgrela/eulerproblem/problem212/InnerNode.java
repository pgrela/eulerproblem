package pgrela.eulerproblem.problem212;

class InnerNode implements Node {
    final int from, to;
    final Node left, right;
    int coveredTimes = 0;
    int coverageCached = 0;

    private InnerNode(int from, int to) {
        this.from = from;
        this.to = to;
        this.left = createNode(from, (from + to) / 2);
        this.right = createNode((from + to) / 2, to);
    }

    private static Node createNode(int from, int to) {
        if (to - from == 1)
            return new Leaf(from);
        return new InnerNode(from, to);
    }

    public static Node createTree(int length) {
        return createNode(0, length);
    }

    @Override
    public void addLayer(int layerFrom, int layerTo) {
        if (iDontCare(layerFrom, layerTo)) return;
        if (amIContained(layerFrom, layerTo)) {
            coverageCached = to - from;
            ++coveredTimes;
            return;
        }
        left.addLayer(layerFrom, layerTo);
        right.addLayer(layerFrom, layerTo);
        refreshTotalCoverageCachedIfNeeded();
    }

    @Override
    public void removeLayer(int layerFrom, int layerTo) {
        if (iDontCare(layerFrom, layerTo)) return;
        if (amIContained(layerFrom, layerTo)) {
            --coveredTimes;
            refreshTotalCoverageCachedIfNeeded();
            return;
        }
        left.removeLayer(layerFrom, layerTo);
        right.removeLayer(layerFrom, layerTo);
        refreshTotalCoverageCachedIfNeeded();
    }

    private void refreshTotalCoverageCachedIfNeeded() {
        if (coveredTimes == 0)
            coverageCached = left.getCoveredArea() + right.getCoveredArea();
    }

    @Override
    public int getCoveredArea() {
        return coverageCached;
    }

    private boolean amIContained(int layerFrom, int layerTo) {
        return layerFrom <= from && to < layerTo;
    }

    private boolean iDontCare(int layerFrom, int layerTo) {
        return layerTo < from || to <= layerFrom;
    }

    @Override
    public String toString() {
        return "InnerNode{" +
                "from=" + from +
                ", to=" + to +
                ", coveredTimes=" + coveredTimes +
                ", coverageCached=" + coverageCached +
                '}';
    }
}
