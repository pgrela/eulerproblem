package pgrela.eulerproblem.common;

import java.util.stream.Stream;

public class Arrays {
    public static int[][] copyArrayStructureAndFillWith(int[][] map, int fillWithValue) {
        return Stream.of(map)
                .map((t) -> t.clone())
                .peek(a -> java.util.Arrays.fill(a, fillWithValue))
                .toArray(int[][]::new);
    }
}
