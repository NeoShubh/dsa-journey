package com.example.dsa.graphs;

import java.util.*;

public class LadderLength127 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean found = false;
        for (String word : wordList) {
            if (Objects.equals(word, endWord)) {
                found = true;
                break;
            }
        }
        if (!found) {
            return 0;
        }
        wordList.add(beginWord);
        HashMap<String, List<String>> mp = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String s = word.substring(0, i) + "*" + word.substring(i + 1, word.length());
                if (mp.containsKey(s)) {
                    mp.get(s).add(word);
                } else {
                    mp.put(s, new ArrayList<>());
                    mp.get(s).add(word);
                }
            }
        }

        // System.out.println(mp);
        HashSet<String> visits = new HashSet<>();
        visits.add(beginWord);
        int result = 1;
        Deque<String> dq = new ArrayDeque<>();
        dq.offer(beginWord);
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int k = 0; k < size; k++) {
                String w = dq.pollFirst();
                // System.out.println(w);
                // System.out.println(visits);
                if (Objects.equals(w, endWord))
                    return result;
                for (int i = 0; i < w.length(); i++) {
                    String str = w.substring(0, i) + "*" + w.substring(i + 1, w.length());
                    for (String s : mp.get(str)) {
                        if (!visits.contains(s)) {
                            if (s.equals(endWord))
                                return result + 1;

                            visits.add(s);
                            dq.add(s);
                        }
                    }
                }
            }
            result++;
        }

        return 0;
    }

    public static void main(String[] args) {
        LadderLength127 obj = new LadderLength127();
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        System.out.println(obj.ladderLength(beginWord, endWord, wordList));

    }
}
