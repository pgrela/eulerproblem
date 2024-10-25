package pgrela.eulerproblem.problem212;

public class Leaf implements Node {
    final int point;
    int count = 0;

    public Leaf(int point) {
        this.point = point;
    }

    @Override
    public void addLayer(int layerFrom, int layerTo) {
        if (amIContained(layerFrom, layerTo))
            ++count;
    }

    @Override
    public void removeLayer(int layerFrom, int layerTo) {
        if (amIContained(layerFrom, layerTo))
            --count;
    }

    @Override
    public int getCoveredArea() {
        return count > 0 ? 1 : 0;
    }

    private boolean amIContained(int layerFrom, int layerTo) {
        return layerFrom <= point && point < layerTo;
    }
}
