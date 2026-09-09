package com.example.dsa.heap;

import java.util.*;

class Pair6 {
    char c;
    int freq;
    int cooldown;

    public Pair6(char c, int freq) {
        this.c = c;
        this.freq = freq;
        this.cooldown = 0;
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;

        HashMap<Character, Integer> hm = new HashMap<>();
        PriorityQueue<Pair6> q = new PriorityQueue<>((a, b) -> b.freq - a.freq);

        for (int i = 0; i < tasks.length; i++) {
            if (hm.containsKey(tasks[i])) {
                hm.put(tasks[i], hm.get(tasks[i]) + 1);
            } else {
                hm.put(tasks[i], 1);
            }
        }
        System.out.println(hm);

        for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
            Pair6 p = new Pair6(entry.getKey(), entry.getValue());
            q.offer(p);
        }
        int time = 0;

        Queue<Pair6> tempQ = new LinkedList<>();
        int ans = 0;

        while (q.size() > 0 || tempQ.size() > 0) {

            if (tempQ.size() > 0 && tempQ.peek().cooldown <= time) {
                Pair6 p = tempQ.poll();
                q.offer(p);
            }

            if (q.size() > 0) {
                Pair6 p = q.poll();
                p.freq--;
                if (p.freq > 0) {
                    p.cooldown = time + n + 1;
                    tempQ.offer(p);
                }
            }
            ans++;
            time++;
        }
        System.out.println(ans);
    }
}
