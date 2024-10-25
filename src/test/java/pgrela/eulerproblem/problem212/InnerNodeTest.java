package pgrela.eulerproblem.problem212;

import junit.framework.TestCase;
import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class InnerNodeTest {
    @Test
    public void shouldNullify() {
        Node root = InnerNode.createTree(19);

        root.addLayer(1,5);
        root.addLayer(2,7);
        root.removeLayer(1,5);
        root.removeLayer(2,7);

        then(root.getCoveredArea()).isZero();
    }
    @Test
    public void shouldAddUp() {
        Node root = InnerNode.createTree(19);

        root.addLayer(1,5); // .++++...........
        root.addLayer(2,7); // .++++++.........
        root.addLayer(8,16);// .++++++.++++++++

        then(root.getCoveredArea()).isEqualTo(14);
    }
    @Test
    public void shouldAddTheSame() {
        Node root = InnerNode.createTree(19);

        root.addLayer(1,5);
        root.addLayer(1,5);
        root.addLayer(1,5);

        then(root.getCoveredArea()).isEqualTo(4);

        root.removeLayer(1,5);
        root.removeLayer(1,5);
        root.removeLayer(1,5);

        then(root.getCoveredArea()).isEqualTo(0);
    }
}