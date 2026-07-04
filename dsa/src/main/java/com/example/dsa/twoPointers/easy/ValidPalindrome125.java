package com.example.dsa.twoPointers.easy;

public class ValidPalindrome125 {
    public static void main(String[] args) {
//        String oldStr = "A man, a plan, a canal: Panama";
        String oldStr = "0P";
        oldStr = oldStr.toLowerCase();

        String s = new String();
      for(int i=0;i<oldStr.length();i++){
          if(oldStr.charAt(i) >= 'a' && oldStr.charAt(i)<='z' ||oldStr.charAt(i) >= '0' && oldStr.charAt(i)<='9' )
              s+=oldStr.charAt(i);
      }

//        if (s.length() == 1) {
//            System.out.println("in");
//            if (s.charAt(0) >= 'a' && s.charAt(0) <= 'z') {
//                System.out.println("False");
//            } else if (s.charAt(0) == ' ')
//                System.out.println("True");
//        }
        System.out.println(s);

        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            System.out.println(start + " " + end);
            System.out.println(s.charAt(start) + " " + s.charAt(end));
            if (s.charAt(start) != s.charAt(end)) {
                System.out.println(false);
//                return false;
            }
            start++;
            end--;
        }
        System.out.println(true);
//        return true;
    }
}
