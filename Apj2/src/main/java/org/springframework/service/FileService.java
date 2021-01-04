package org.springframework.service;


import java.io.File;


public class FileService {
    private static final String PATH = "C:/Users/Zangar/Desktop/apj2";

    public static File[] getFilesFromPath() {
        File file = new File(PATH);
        return file.listFiles();
    }

}
