package com.example.dsa.linkedList;

public class ReverseKNodeLinkedList25 {
    public static void main(String [] args){
        ListNode head = null;int k=2;
        ListNode dummy = head;
        int sizeOfLL = 0;

        while (dummy != null) {
            sizeOfLL++;
            dummy = dummy.next;
        }
        dummy = head;
        int x = 1;
        int i = 1;
        int rounds = sizeOfLL / k;
        ListNode prev = null;

        while (rounds-- > 0) {

            ListNode oldHead = dummy;
            ListNode newHead = reverse(dummy, k);

            // First group
            if (oldHead == head) {
                head = newHead;
            }

            // Connect previous group to current group
            if (prev != null) {
                prev.next = newHead;
            }

            // oldHead is now the tail of reversed group
            prev = oldHead;

            // Move to next group
            dummy = oldHead.next;
        }
//        return head;
    }
    static ListNode reverse(ListNode l1, int k) {
        ListNode curr = l1;
        ListNode prev = null;

        while (curr != null && k-- > 0) {
            ListNode nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }

        l1.next = curr;   // connect old head/tail to next group

        return prev;
    }
}
