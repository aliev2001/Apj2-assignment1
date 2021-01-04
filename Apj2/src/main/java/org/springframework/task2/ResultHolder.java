package org.springframework.task2;

import java.util.HashMap;
import java.util.Map;

public class ResultHolder {
    public static Map<String,Integer> occurrence=new HashMap<>();


    public static void addOccurrence(String file,Integer result){
        ResultHolder.occurrence.put(file,result);
    }

    public static Map<String, Integer> getOccurrence() {
        return ResultHolder.occurrence;
    }

    public static void setOccurrence(Map<String, Integer> occurrence) {
        ResultHolder.occurrence = occurrence;
    }
}
