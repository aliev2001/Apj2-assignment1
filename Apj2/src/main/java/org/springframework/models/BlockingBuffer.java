package org.springframework.models;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer implements Buffer<File> {

    private final ArrayBlockingQueue<File> buffer;

    public BlockingBuffer() {
        this.buffer = new ArrayBlockingQueue<>(1);
    }

    @Override
    public void blockingRenameAndPut(File data) throws InterruptedException {
        int res = new Scanner(data.getName()).useDelimiter("\\D+").nextInt();

        int index=data.getName().lastIndexOf('.');
        String extension=data.getName().substring(index);

        if (data.renameTo(new File(data.getParent() + "\\" + res + extension))) {
            System.out.println("File was changed");
            buffer.put(data); // place value in buffer
        }
    }

    @Override
    public File blockingGet() throws InterruptedException {
        return buffer.take();
    }

    public Iterable<File> getFiles() {
        return buffer;
    }
}
