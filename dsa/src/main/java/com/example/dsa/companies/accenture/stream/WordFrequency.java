package com.example.dsa.companies.accenture.stream;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequency {
    public static void main(String [] args){
        String s = "abbaesqq";

    Map<String,Long> mp =     Arrays.stream(s.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(mp);
    }

}
