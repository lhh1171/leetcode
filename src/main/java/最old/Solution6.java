package 最old;

public class Solution6 {
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        //没有结点
        if (head == null) {
            head = node;
            head.next = node;
            return head;
        }
        Node cur = head;
        Node next = head.next;
        while (next != head) {
            //在非降序的位置
            if (cur.val <= insertVal && insertVal <= next.val) {
                cur.next = node;
                node.next = next;
                return head;
            }
            //在降序的位置
            if (cur.val > next.val) {
                //此时一定是最大的结点和最小的结点，如果insertVal比当前结点（最大值）的值大
                //或者比下一个结点（最小值）的值小 插入即可
                if (cur.val <= insertVal || next.val >= insertVal) {
                    cur.next = node;
                    node.next = next;
                    return head;
                }
            }
            cur = cur.next;
            next = next.next;
        }
        //正好位置在head之前 此时next = head不进循环
        cur.next = node;
        node.next = next;
        return head;
    }

    static class Node {
        int val;
        Node next;
        public Node(int val){
            this.val=val;
        }
    }
}
