package com.example.dsa.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

class MedianFinder {
    PriorityQueue<Double> smallHeap;
    PriorityQueue<Double> largeHeap;

    public MedianFinder() {
        smallHeap = new PriorityQueue<>(Comparator.reverseOrder());
        largeHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // add number
        smallHeap.add(Double.valueOf(num));

        if (!largeHeap.isEmpty() && smallHeap.peek() > largeHeap.peek()) {
            largeHeap.add(smallHeap.poll());
        }

        while (Math.abs(smallHeap.size() - largeHeap.size()) > 1) {
            if (smallHeap.size() > largeHeap.size()) {
                largeHeap.add(smallHeap.poll());
            } else {
                smallHeap.add(largeHeap.poll());
            }
        }

    }

    public double findMedian() {

     if(Math.abs(smallHeap.size() - largeHeap.size())==0){
        return  (smallHeap.peek()+ largeHeap.peek())/2;
     }else{
         if(smallHeap.size()> largeHeap.size()){
             return smallHeap.peek();
         }else return largeHeap.peek();
     }

    }

    public static void main(String[] args) {

        MedianFinder mf = new MedianFinder();

        mf.addNum(1);
        mf.addNum(2);

        System.out.println(mf.findMedian());

        mf.addNum(3);

        System.out.println(mf.findMedian());
    }
}