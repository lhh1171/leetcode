package Bycategory.链表;

public class Solution24 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode swapTwoNode(ListNode head){
        ListNode dummyHead=new ListNode();
        dummyHead.next=head;
        ListNode cur=dummyHead;
        ListNode temp1,temp2;
        while (cur.next!=null&&cur.next.next!=null){
            temp1=cur.next;
            temp2=cur.next.next.next;
            cur.next=cur.next.next;
            cur.next.next=temp1;
            temp1.next=temp2;
            cur=cur.next.next;
        }
        return dummyHead.next;
    }
}
