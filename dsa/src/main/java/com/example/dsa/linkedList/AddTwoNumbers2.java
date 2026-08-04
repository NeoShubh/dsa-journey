package com.example.dsa.linkedList;

public class AddTwoNumbers2 {
    public static void main(String [] args){}
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int carry = 0;
            ListNode head = null;

            while(l1 != null && l2 != null) {
                int sum = l1.val + l2.val + carry;
                int data = sum % 10;
                carry = sum / 10;
                ListNode newNode = new ListNode(data);
                if(head == null) {
                    head = newNode;
                } else {
                    ListNode last = head;
                    while(last.next != null) last = last.next;
                    last.next = newNode;
                }
                l1 = l1.next;
                l2 = l2.next;
            }

            while(l2 != null) {
                int sum = carry + l2.val;
                carry = sum / 10;
                int data = sum % 10;
                ListNode newNode = new ListNode(data);
                ListNode last = head;
                while(last.next != null) last = last.next;
                last.next = newNode;
                l2 = l2.next;
            }

            while(l1 != null) {
                int sum = carry + l1.val;
                carry = sum / 10;
                int data = sum % 10;
                ListNode newNode = new ListNode(data);
                ListNode last = head;
                while(last.next != null) last = last.next;
                last.next = newNode;
                l1 = l1.next;
            }

            if(carry > 0) {
                ListNode newNode = new ListNode(carry);
                ListNode last = head;
                while(last.next != null) last = last.next;
                last.next = newNode;
            }

            return head;
        }
    }
}
