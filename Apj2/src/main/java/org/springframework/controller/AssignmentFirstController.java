package org.springframework.controller;

import org.springframework.models.BlockingBufferTest;
import org.springframework.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;

@RestController
public class AssignmentFirstController {

    @GetMapping("/firstTask")
    public Iterable<File> getChangedFiles() {
        try {
            BlockingBufferTest.main();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(FileService.getFilesFromPath());
    }
}
