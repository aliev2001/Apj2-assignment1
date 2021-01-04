package org.springframework.task2;

import java.io.File;
import java.io.IOException;

public interface Buffer {
    void syncPutAndSearch(String word, File file) throws InterruptedException, IOException;

    File syncGet() throws InterruptedException;
}
