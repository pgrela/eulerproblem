package pgrela.eulerproblem.common;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

public class Files {
    public static String getFileAsString(String filename){
        try{
            return new String(readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
