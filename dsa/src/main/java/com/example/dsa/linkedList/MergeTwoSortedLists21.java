package com.example.dsa.linkedList;

public class MergeTwoSortedLists21 {
    public static void main(String [] args){
        ListNode list1 = null;
        ListNode list2 = null;
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                tail.next = list2;
                list2 = list2.next;
            } else {
                tail.next = list1;
                list1 = list1.next;
            }
            tail = tail.next;
        }

        if (list1 != null) {
            tail.next = list1;
        }

        if (list2 != null) {
            tail.next = list2;
        }
//        return dummy.next;
    }

}
