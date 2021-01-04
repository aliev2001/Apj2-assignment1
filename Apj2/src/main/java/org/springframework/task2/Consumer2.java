package org.springframework.task2;

import org.springframework.service.FileService;

import java.io.File;
import java.security.SecureRandom;


public class Consumer2 implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final Buffer sharedLocation;

    public Consumer2(Buffer sharedLocation) {
        this.sharedLocation = sharedLocation;
    }

    @Override
    public void run() {
        for (File file : FileService.getFilesFromPath()) {
            try {
                File file1 = sharedLocation.syncGet();
                System.out.println("Consumer read now " + file1.getName());
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }
}