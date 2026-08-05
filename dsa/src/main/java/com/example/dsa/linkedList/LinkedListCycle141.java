package com.example.dsa.linkedList;

public class LinkedListCycle141 {
    public static void main(String [] args){
        ListNode head = null;
        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast) System.out.println("true");
//                return true;
        }
//        return false;

    }

}
