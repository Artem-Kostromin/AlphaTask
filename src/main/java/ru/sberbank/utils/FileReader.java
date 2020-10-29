package ru.sberbank.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    private static final Logger logger = Logger.getLogger(FileReader.class.getName());

    public static long countRows(Path path){
        long numberOfRow = 0;
        try {
            numberOfRow = Files.lines(path).count();
        } catch (IOException e) {
            logger.error("Exception: ", e);
        }
        return numberOfRow;
    }
}
