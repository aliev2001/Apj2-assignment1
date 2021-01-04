package org.springframework.models;

public interface Buffer<T> {
    void blockingRenameAndPut(T data) throws InterruptedException;
    T blockingGet() throws InterruptedException;
}
