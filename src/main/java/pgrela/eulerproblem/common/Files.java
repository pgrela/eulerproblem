package pgrela.eulerproblem.common;

import static java.nio.file.Files.readAllBytes;
import static java.util.stream.Stream.of;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Files {
    public static String getFileAsString(String filename){
        try{
            return new String(readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stream<String> getLinesStream(String filename) {
        try {
            return java.nio.file.Files.lines(Paths.get(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[][] fileTo2DArray(String split, String resourceFile) {
        return getLinesStream(resourceFile).map(line -> of(line.split(split)).mapToInt(Integer::valueOf).toArray()).toArray(int[][]::new);
    }
}
