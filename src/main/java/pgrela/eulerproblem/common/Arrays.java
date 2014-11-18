package pgrela.eulerproblem.common;

import java.util.stream.Stream;

public class Arrays {
    public static int[][] copyArrayStructureAndFillWith(int[][] map, int fillWithValue) {
        return Stream.of(map)
                .map((t) -> t.clone())
                .peek(a -> java.util.Arrays.fill(a, fillWithValue))
                .toArray(int[][]::new);
    }

    public static void reverse(int[] integers) {
        for (int i = 0; i < integers.length / 2; i++) {
            int temporary = integers[i];
            integers[i] = integers[integers.length - i - 1];
            integers[integers.length - i - 1] = temporary;
        }
    }
}
