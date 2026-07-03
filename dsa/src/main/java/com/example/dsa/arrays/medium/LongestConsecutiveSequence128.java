package com.example.dsa.arrays.medium;

import java.util.HashSet;
//So I was thinking that if is there any way I can find out that the number I have right lets say its 4 so
//I want to know that in the list do I have 5 or 3 and if yes then can we move ahead like if I have 5 then find
//6 if 6 is found then find 7 and so on like this I will find different length out of which I will find the maximum
// after that I think more lets say why would I go on with previous element if we find the next why not go with it
// I mean that element too will make a list lets say I have 3,4,5 and if I am at 3 I will look for 4 and then 5
// if I am at 4 I will look for 5 so in the end there will be three lenght 3 or 2 or 1 max is 3 I will pick it up
// but if is there anything I can avoid that too Yes there is in this code i put them all in hashset so that
// I can use contains all of the time and the lookup will be O(n). after that I iterate over the set and I just
//avoid that Do i have previous element if yes avoid the current element must be unique like 3 shoudl be the first
// it will cover for next elements as well with that . inside that if condition I declare two var one for the temp
//length and next would be the element which i will be incrementing each time if the match is avaialable after that
// I will put the max length
public class LongestConsecutiveSequence128 {
    public static void main(String[] args) {

        int[] nums = {100, 4, 200, 1, 3, 2};
//        int [] nums = {0,3,7,2,5,8,4,6,0,1};
//        int [] nums = {1,0,1,2};
        HashSet<Integer> st = new HashSet<>();

        for (int num : nums) {
            st.add(num);
        }
        int maxlength = 0;

        for (int num : st) {
            System.out.println("num is : " + num);

            if (!st.contains(num - 1)) {
                int len = 1;
                int nxtNum = num;
                while (st.contains(nxtNum + 1)) {
                    nxtNum++;
                    len++;
                }
                maxlength = Math.max(maxlength, len);
            }
        }
        System.out.println(maxlength);
    }
}
