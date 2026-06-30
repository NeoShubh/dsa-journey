package com.example.dsa.arrays.medium;

import java.util.*;

public class GroupAnagram49 {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
////        String[] strs = {""};
//        String[] strs = {"a"};

        HashMap<String, List<String>> mp = new HashMap<>();
        List<List<String>> answerList = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {

            //sorted string
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String key = new String(c);

            if (mp.containsKey(key)) {
                mp.get(key).add(strs[i]);
            } else {
                List<String> lt = new ArrayList<>();
                lt.add(strs[i]);
                mp.put(key, lt);
            }

        }
        for(Map.Entry<String,List<String>> entry : mp.entrySet()){
            answerList.add(entry.getValue());
        }
        System.out.println(mp);
        System.out.println(answerList);
    }
}

//comments included code
//for (int i = 0; i < strs.length; i++) {
//
//List<String> list = new ArrayList<>();
//boolean matchFound = false;
//char[] c = strs[i].toCharArray();
//            Arrays.sort(c);
//            System.out.println("the string is "+strs[i]+" and sorted look is ");
//
//            for(char ch : c)
//        {
//        System.out.print(ch);
//            }
//                    System.out.println();
//            for (int j = 0; j < answerList.size(); j++) {
//char[] x = answerList.get(j).get(0).toCharArray();
//                Arrays.sort(x);
//int flg = Arrays.compare(c, x);
//                System.out.println("answerlist comparison");
//                System.out.println(c);
//                System.out.println(x);
//                System.out.println("flag is "+flg);
//                if (flg == 0) {
//        answerList.get(j).add(strs[i]);
//                    System.out.println("match found");
//                    System.out.println(answerList);
//matchFound = true;
//        break;
//        }
//        }
//        if (!matchFound) {
//        list.add(strs[i]);
//                answerList.add(list);
//                System.out.println("match not found");
//                System.out.println(list);
//                System.out.println(answerList);
//            }
//
//                    }