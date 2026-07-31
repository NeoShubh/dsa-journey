package com.example.dsa.linkedList;

class ListNode {
      int val;
      ListNode next;
     ListNode() {}
    ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

public class reverseLinkedList206 {
    public static void main(String [] args){
        ListNode head= null;
        ListNode prev = null;
        ListNode curr = head;
        ListNode nxt = null;
        while(curr!=null){
            nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }
//        return prev;
    }
}
