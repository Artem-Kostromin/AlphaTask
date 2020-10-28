import handlers.impl.DeleteFileHandler;
import handlers.Handler;
import handlers.impl.JsonHandler;
import handlers.impl.XMLHandler;

public class EventDistributor {
    public static void distribute(String filesExtension, String fileDir) {
        Handler handler;
        if (filesExtension.equals("xml")) handler = new XMLHandler(fileDir);
        else if (filesExtension.equals("json")) handler = new JsonHandler(fileDir);
        else handler = new DeleteFileHandler(fileDir);

        Thread thread = new Thread(handler);
        thread.start();
    }
}
