package ru.sberbank.handlers.impl;

import org.apache.log4j.Logger;
import ru.sberbank.handlers.Handler;
import ru.sberbank.utils.FileReader;

import java.nio.file.Path;

public class XMLHandler implements Handler {
    private static final Logger logger = Logger.getLogger(XMLHandler.class.getName());
    private final Path fileDir;

    public XMLHandler(Path fileDir) {
        this.fileDir = fileDir;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long numberOfRow = FileReader.countRows(fileDir);
        long endTime = System.currentTimeMillis();

        logger.info("Analyze " + fileDir.getFileName() + ": Number of rows = " + numberOfRow + " ;Time spent = " + (endTime - startTime) + "ms.");
    }
}
