import utils.Extractor;

import java.io.IOException;
import java.nio.file.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getLogger(Main.class.getName());
    public static final String watchPath = "/Users/user1234/IdeaProjects/AlphaTask/src/watchFolder/";

    public static void main(String[] args) {
        WatchService watchService = null;
        try {
            //FileHandler fh = new FileHandler("loggerOutput");
            //logger.addHandler(fh);

            watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(watchPath);

            path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    String stringEvent = getNameOfEvent(event.kind());

                    Path filesPath = Paths.get(watchPath + event.context());
                    String filesName = Extractor.getFileName(filesPath);


                    //String filesName = event.context().toString().split("\\.")[0];
                    String filesExtension = event.context().toString().split("\\.")[1];
                    String fileDirectory = watchPath + event.context();
                    logger.info("File " + filesName + " with extension " + filesExtension + stringEvent);
                    if (event.kind().toString().matches("ENTRY_CREATE|ENTRY_MODIFY")) EventDistributor.distribute(filesExtension, fileDirectory);
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, "Exception: ", e);
        }
    }

    private static String getNameOfEvent(WatchEvent.Kind<?> kind){
        return " was created";
    }
}
