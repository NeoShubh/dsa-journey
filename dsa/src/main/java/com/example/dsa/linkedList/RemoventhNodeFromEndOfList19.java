package com.example.dsa.linkedList;

public class RemoventhNodeFromEndOfList19 {
    public static  void main(String [] args){
        ListNode head=null;
        int n =3;
        ListNode counter = head;
        int sizeOfList = 0;
        while (counter != null) {
            sizeOfList++;
            counter = counter.next;
        }
        System.out.println(sizeOfList);

        ListNode LastNode = head;
        ListNode nthNode = head;
        int x = 0;

        int cmp = sizeOfList - n - 1;
        if (cmp < 0) {
//            return head.next;
        }

        while (x <= cmp) {
            if (x <= cmp - 1) {
                LastNode = LastNode.next;
            }
            nthNode = nthNode.next;
            x++;
        }
        System.out.println(LastNode.val + " " + nthNode.val);
        LastNode.next = nthNode.next;

    }
}
