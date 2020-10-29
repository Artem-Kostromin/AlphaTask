package ru.sberbank;

import org.apache.log4j.Logger;
import ru.sberbank.utils.Extractor;

import java.io.IOException;
import java.nio.file.*;
import java.util.Date;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static final Path defaultPath = Paths.get(System.getProperty("user.dir") + "/defaultWatchFolder");
    public static final String separator = FileSystems.getDefault().getSeparator();
    public static Path path = null;

    public static void main(String[] args) {
        path = Extractor.getPath(defaultPath);

        WatchService watchService = null;
        try {
            watchService = FileSystems.getDefault().newWatchService();

            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    Path filesPath = Paths.get(path + separator + event.context());
                    String filesEvent = Extractor.getEvent(event.kind());

                    logger.info("File " + event.context() + " " + filesEvent + " at " + new Date().getTime());
                    if (filesEvent.equals("created")) EventDistributor.distribute(filesPath);
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            logger.error("Exception: ", e);
        }
    }
}
