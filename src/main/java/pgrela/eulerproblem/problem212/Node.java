package pgrela.eulerproblem.problem212;

public interface Node {
    void addLayer(int layerFrom, int layerTo);

    void removeLayer(int layerFrom, int layerTo);

    int getCoveredArea();
}
