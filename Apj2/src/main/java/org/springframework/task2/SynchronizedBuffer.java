package org.springframework.task2;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class SynchronizedBuffer implements Buffer {
    private File buffer;
    private volatile boolean occupied = false;

    @Override
    public synchronized void syncPutAndSearch(String word, File data) throws InterruptedException, IOException {
        int count = 0;
        while (occupied) {
            System.out.println("Producer tries to write.");
            wait();
        }
        buffer = data;
        occupied = true;
        try {
            if(data.getName().substring(data.getName().lastIndexOf('.')).equals(".pdf")){
                PDDocument document = PDDocument.load(data);
                //Instantiate PDFTextStripper class
                PDFTextStripper pdfStripper = new PDFTextStripper();
                //Retrieving text from PDF document
                String text = pdfStripper.getText(document);
                String[] words=text.split(" ");
                for(String part:words){
                    if(part.equals(word)){
                        count++;
                    }
                }
                document.close();
            }else{
                Scanner scanner = new Scanner(data);
                while (scanner.hasNext()) {
                    if (scanner.next().equals(word))
                        count++;
                }
            }




            ResultHolder.addOccurrence(data.getName(), count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        notifyAll();

    }

    @Override
    public synchronized File syncGet() throws InterruptedException {
        while (!occupied) {
            System.out.println("Consumer tries to read.");
            wait();
        }

        occupied = false;
        notifyAll();
        return buffer;
    }
}


