package Bycategory.手写数据结构;

public class SkipList {
    private static final float RANDOM_P = 0.5f;

    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;

    private Node head = new Node();  // 带头链表

    public class Node {
        private int data = -1;
        private Node forwards[] = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");

            return builder.toString();
        }
    }

    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    private int randomLevel() {
        int level = 1;

        while (Math.random() < RANDOM_P && level < MAX_LEVEL)
            level += 1;
        return level;
    }

    public void insert(int value) {
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node update[] = new Node[level];
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }

        // record every level largest value which smaller than insert value in update[]
        Node p = head;
        for (int i = level - 1; i >= 0; --i) {
            //一直往前移动到p.forwards[i].data <= value
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }

            // use update save node in search path
            //每一层都要插入到合适的区间
            update[i] = p;
        }

        // in search path node next node become new node forwords(next)
        for (int i = 0; i < level; ++i) {
            //newNode第i层是指向update[i].forwards[i];
            newNode.forwards[i] = update[i].forwards[i];
            //第i层插入newNode
            update[i].forwards[i] = newNode;
        }

        // update node hight
        if (levelCount < level) levelCount = level;
    }


    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;
        //确定每一层要改变的元素update[i]
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        //进行改变
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (update[i].forwards[i] != null &&
                        update[i].forwards[i].data == value) {
                    //往前多指向一个
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }

        while (levelCount > 1 && head.forwards[levelCount] == null) {
            levelCount--;
        }

    }

    public void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

}
