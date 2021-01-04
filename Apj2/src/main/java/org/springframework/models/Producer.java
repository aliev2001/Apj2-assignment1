package org.springframework.models;

import org.springframework.service.FileService;

import java.io.File;
import java.security.SecureRandom;

public class Producer implements Runnable {
    private final Buffer<File> sharedLocation;
    private final SecureRandom generator = new SecureRandom();

    public Producer(Buffer<File> sharedLocation) {
        this.sharedLocation = sharedLocation;
    }

    @Override
    public void run() {
        File[] files = FileService.getFilesFromPath();
        for (int i = 0; i < files.length; i++) {
            try {
                Thread.sleep(generator.nextInt(3000));
                sharedLocation.blockingRenameAndPut(files[i]);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("Producer put " + files[i].getName());
            }

        }
        System.out.printf("Producer done producing%nTerminating Producer%n");

    }
}
