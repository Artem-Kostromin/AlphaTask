package handlers.impl;

import handlers.Handler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLHandler implements Handler {
    public static Logger logger = Logger.getLogger(XMLHandler.class.getName());
    private final String fileDir;

    public XMLHandler(String fileDir) {
        this.fileDir = fileDir;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long numberOfRow = 0;

        try {
            FileReader fileReader = new FileReader(fileDir);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()){
                numberOfRow++;
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Exception: ", e);
        }

        long endTime = System.currentTimeMillis();

        logger.info("");
    }
}
