package handlers.impl;

import handlers.Handler;

import java.io.File;

public class DeleteFileHandler implements Handler {
    private String fileDir;

    public DeleteFileHandler(String fileDir) {
        this.fileDir = fileDir;
    }

    @Override
    public void run() {
        File file = new File(fileDir);
        file.delete();
    }
}
