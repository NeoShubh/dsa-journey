package com.example.dsa.contests.contest510;

public class ProblemOne {
    public static void main(String[] args) {
        String startTime = "01:00:00", endTime = "01:00:25";
        String[] startT = startTime.split(":");
        String[] endT = endTime.split(":");
        int totalSeconds = 0;

//        for (int i = 0; i < startT.length; i++) {
//            System.out.println(startT[i] + " ");
//        }
//
//        for (int i = 0; i < endT.length; i++) {
//            System.out.println(endT[i] + " ");
//        }
//

        totalSeconds = (Integer.parseInt(endT[0])*3600 +
                +Integer.parseInt(endT[1]) * 60 +
                +Integer.parseInt(endT[2]) ) -
                (Integer.parseInt(startT[0]) * 3600 +
                Integer.parseInt(startT[1])*60 +
                Integer.parseInt(startT[2]));
        System.out.println(totalSeconds);
    }
}
