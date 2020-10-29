package ru.sberbank.utils;

import org.apache.log4j.Logger;
import ru.sberbank.Main;

import java.nio.file.*;
import java.util.Scanner;

public class Extractor {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Extractor.class.getName());

    public static String getFileName(Path path){
        return path.getFileName().toString().split("\\.")[0];
    }
    public static String getFileExtension(Path path){
        return path.getFileName().toString().split("\\.")[1];
    }

    public static Path getPath(Path defaultPath){
        System.out.print("Enter the directory you want to listen to (if you want chose default print \"default\"): ");
        Path path = null;
        while (true){
            path = Paths.get(scanner.nextLine());
            if (path.toString().equals("default")){
                path = defaultPath;
                break;
            }else if (Files.exists(path)){
                break;
            }
            System.out.println("There is no such folder. Try again!");
        }
        logger.info("Directory " + path + " is being tapped.");
        return path;
    }

    public static String getEvent(WatchEvent.Kind<?> kind) {
        if (kind == StandardWatchEventKinds.ENTRY_CREATE) return "created";
        if (kind == StandardWatchEventKinds.ENTRY_MODIFY) return "modified";
        else return " deleted!";
    }
}
