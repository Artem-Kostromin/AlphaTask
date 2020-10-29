package ru.sberbank;

import ru.sberbank.handlers.impl.DeleteFileHandler;
import ru.sberbank.handlers.Handler;
import ru.sberbank.handlers.impl.JsonHandler;
import ru.sberbank.handlers.impl.XMLHandler;
import ru.sberbank.utils.Extractor;

import java.nio.file.Path;

public class EventDistributor {
    public static void distribute(Path path) {
        Handler handler;
        if (Extractor.getFileExtension(path).equals("xml")) handler = new XMLHandler(path);
        else if (Extractor.getFileExtension(path).equals("json")) handler = new JsonHandler(path);
        else handler = new DeleteFileHandler(path);

        Thread thread = new Thread(handler);
        thread.start();
    }
}
