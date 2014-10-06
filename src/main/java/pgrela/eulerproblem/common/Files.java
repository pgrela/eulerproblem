package pgrela.eulerproblem.common;

import static java.nio.file.Files.readAllBytes;

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
}
