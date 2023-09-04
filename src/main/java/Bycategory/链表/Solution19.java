package Bycategory.链表;

public class Solution19 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }
    // 双指针
    public ListNode removeNthFromEnd(ListNode head,int n) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode fast = dummyHead;
        ListNode slow = dummyHead;
        n++;
        while (n-- != 0 && fast != null) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
            slow.next = slow.next.next;
        }
        return dummyHead.next;
    }
}
