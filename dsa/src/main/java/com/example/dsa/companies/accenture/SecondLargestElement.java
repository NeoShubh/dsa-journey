package com.example.dsa.companies.accenture;

public class SecondLargestElement {
    public static void main(String[] args) {
        int[] arr = {12, 35, 21, 36, 88, 12};

        int firstLargest = 0;
        int secondLargest = 0;

        for (int i = 0; i < arr.length; i++) {
            if (firstLargest < arr[i]) {

                secondLargest = firstLargest;
                firstLargest = arr[i];
            } else if (secondLargest < arr[i])
                secondLargest = arr[i];
        }
        System.out.println(firstLargest + " " + secondLargest);
    }
}
