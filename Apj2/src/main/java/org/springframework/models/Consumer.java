package org.springframework.models;

import org.springframework.service.FileService;

import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Consumer implements Runnable {
    private final Buffer<File> sharedLocation;
    private FileService fileService;
    private final SecureRandom generator=new SecureRandom();

    public Consumer(Buffer<File> sharedLocation) {
        this.sharedLocation = sharedLocation;
    }

    @Override
    public void run() {
        ArrayList<File> files=new ArrayList<>();
        for (File file:FileService.getFilesFromPath()) {
            try
            {
                Thread.sleep(generator.nextInt(3000));
                files.add((File)sharedLocation.blockingGet());
            }
            catch (InterruptedException exception)
            {
                Thread.currentThread().interrupt();
            }
            System.out.println("Consumer read files "+file.getName());

        }
        System.out.println("Changed values "+files);


    }
}
