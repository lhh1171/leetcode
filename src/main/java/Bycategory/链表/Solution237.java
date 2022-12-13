package Bycategory.链表;

public class Solution237 {
    static class Node {
        public int val;
        public Node next = null;
    }

    public Node deleteNode(Node head, int target) {
        Node dummyHead = new Node();
        dummyHead.next = head;
        Node temp = dummyHead;
        while (temp.next != null) {
            if (temp.next.val == target) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dummyHead.next;
    }

}
