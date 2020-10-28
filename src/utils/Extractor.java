package utils;

import java.nio.file.Path;

public class Extractor {
    public static String getFileName(Path path){
        return path.getFileName().toString();
    }
}
