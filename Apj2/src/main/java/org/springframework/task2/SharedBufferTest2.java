package org.springframework.task2;
// Two threads correctly manipulating a synchronized buffer.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedBufferTest2 {
    public static void main(String args) throws InterruptedException {
        // create a newCachedThreadPool
        ExecutorService executorService = Executors.newCachedThreadPool();
        // create SynchronizedBuffer to store ints
        Buffer sharedLocation = new SynchronizedBuffer();
        // execute the Producer and Consumer tasks
        executorService.execute(new Producer2(sharedLocation, args));
        executorService.execute(new Consumer2(sharedLocation));
        executorService.shutdown();
    }
}