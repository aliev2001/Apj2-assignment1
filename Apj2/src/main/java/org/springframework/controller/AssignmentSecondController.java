package org.springframework.controller;

import org.springframework.task2.ResultHolder;
import org.springframework.task2.SharedBufferTest2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AssignmentSecondController {

    @GetMapping("/secondTask")
    public Map<String,Integer> getChangedFiles(@RequestParam(name = "word",required = true)String word) {
        try {
            SharedBufferTest2.main(word);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            return ResultHolder.getOccurrence();
        }
    }
}
