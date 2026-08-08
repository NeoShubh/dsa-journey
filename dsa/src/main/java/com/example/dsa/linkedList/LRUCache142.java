package com.example.dsa.linkedList;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class LRUCache142 {

    private int capacity;
    PriorityQueue<Pair> q = new PriorityQueue<>();

    public LRUCache142(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        return q.stream().filter(pair -> pair.getKey() == key).map(pair -> pair.getValue()).findFirst().orElse(-1);
    }

    public void put(int key, int value) {
        Pair p = new Pair(key, value);
        q.offer(p);
        if (q.size() > capacity)
            q.poll();
    }

    public void print() {
        q.stream().forEach(pair -> System.out.println(pair.getKey() + " " + pair.getValue()));
    }

    public static void main(String[] args) {
        LRUCache142 obj = new LRUCache142(4);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);list.add(2);list.add(2);list.add(2);list.add(2);list.add(2);
        int [] arr = list.stream().mapToInt(Integer::intValue).toArray();
//        arr.le
        obj.put(1, 11);
        obj.put(4, 44);
        obj.put(3, 33);
        obj.put(2, 22);
        obj.put(2, 22);
        obj.print();
    }
}
