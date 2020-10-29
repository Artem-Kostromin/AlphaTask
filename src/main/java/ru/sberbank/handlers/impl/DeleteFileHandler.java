package ru.sberbank.handlers.impl;

import org.apache.log4j.Logger;
import ru.sberbank.handlers.Handler;

import java.io.File;
import java.nio.file.Path;

public class DeleteFileHandler implements Handler {
    private static final Logger logger = Logger.getLogger(DeleteFileHandler.class.getName());
    private Path fileDir;

    public DeleteFileHandler(Path fileDir) {
        this.fileDir = fileDir;
    }

    @Override
    public void run() {
        File file = new File(String.valueOf(fileDir));
        file.delete();
        logger.info("File " + fileDir.getFileName() + " deleted!");
    }
}
