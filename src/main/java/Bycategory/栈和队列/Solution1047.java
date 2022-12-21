package Bycategory.栈和队列;

import java.util.ArrayDeque;

class Solution1047 {
    public String removeDuplicates(String S) {
        //ArrayDeque会比LinkedList在除了删除元素这一点外会快一点
        //参考：https://stackoverflow.com/questions/6163166/why-is-arraydeque-better-than-linkedlist
        ArrayDeque<Character> deque = new ArrayDeque<>();
        char ch;
        for (int i = 0; i < S.length(); i++) {
            ch = S.charAt(i);
            if (deque.isEmpty() || deque.peek() != ch) {
                deque.push(ch);
            } else {
                deque.pop();
            }
        }
        StringBuilder str = new StringBuilder();
        //剩余的元素即为不重复的元素
        while (!deque.isEmpty()) {
            str.insert(0, deque.pop());
        }
        return str.toString();
    }
}