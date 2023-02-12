package Bycategory.手写数据结构;

public class AVLTree {
    static class Node{
        public int val;
        public Node left;
        public Node right;
        public int height;

        public Node(int val, Node left, Node right, int height) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.height = height;
        }

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public int getSize(){
        return size;
    }

    public AVLTree() {
        root = null;
        size = 0;
    }

    //获取某一节点的高度
    public int getHeight(Node node){
        if (node==null){
            return 0;
        }
        return node.height;
    }

    //获取节点的平衡因子
    public int getBalanceFactor(Node node){
        if (node==null){
            return 0;
        }
        return getHeight(node.left)-getHeight(node.right);
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if (node==null){
            return true;
        }
        //取绝对值
        int balanceFactory=Math.abs(getBalanceFactor(node));
        if (balanceFactory>1){
            return false;
        }
        //看左右节点是否平衡
        return isBalanced(node.left)&&isBalanced(node.right);
    }

    //右旋
    /*
    * 对节点y进行向右旋转操作，返回旋转后新的根节点x
            y                             x
           / \                          /   \
          x   T4     向右旋转 (y)        z     y
         / \       - - - - - - - ->    / \   / \
        z   T3                       T1  T2 T3 T4
       / \
     T1   T2
    * */
    private Node rightRotate(Node y){
        Node x=y.left;
        Node t3=y.left.right;
        x.right=y;
        y.left=t3;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    //左旋
    /*
    * 对节点y进行向左旋转操作，返回旋转后新的根节点x
        y                             x
      /  \                          /   \
     T1   x      向左旋转 (y)       y     z
         / \   - - - - - - - ->   / \   / \
       T2  z                     T1 T2 T3 T4
          / \
         T3 T4
    * */
    private Node leftRotate(Node y){
        Node x = y.right;
        Node t2 = x.left;
        x.left = y;
        y.right = t2;
        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(int e) {
        root = add(root, e);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, int e) {
        if (node==null){
            size++;
            return new Node(e);
        }
        //二叉查找树的基础
        if (e<node.val)
            node.left=add(node.left,e);
        else if (e > node.val)
            node.right = add(node.right, e);
        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        //这棵树左边大于右边，需要右旋
        if (balanceFactor>1&&getBalanceFactor(node.left)>0){
            //右旋LL
            return rightRotate(node);
        }
        //这棵树左边小于右边，需要左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) < 0) {
            //左旋RR
            return leftRotate(node);
        }

        //和上面两个一样
        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public int remove(int e) {
        Node node = getNode(root, e);
        if (node != null) {
            root = remove(root, e);
            return node.val;
        }
        return Integer.MIN_VALUE;
    }

    private Node remove(Node node, int e) {
        if (node == null)
            return null;
        //二叉查找树的基础
        Node retNode;
        if (e < node.val) {
            node.left = remove(node.left, e);
            retNode = node;
        } else if (e > node.val) {
            node.right = remove(node.right, e);
            retNode = node;
        } else {
            //匹配成功进行删除
            // e.compareTo(node.e) == 0
            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            // 待删除节点右子树为空的情况
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                // 待删除节点左右子树均不为空的情况
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                /*
                *   2                          2.5
                *1    3     --------------》1        3
                *   2.5  4                               4
                * */
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.val);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }

        if (retNode == null)
            return null;
        //维护平衡
        //更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            //右旋LL
            return rightRotate(retNode);
        }
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            //左旋RR
            return leftRotate(retNode);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            node.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            node.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    //查找
    private Node getNode(Node root, int val) {
        Node p = root;
        while (p != null) {
            //小于找左边，大于找右边
            if (val < p.val) {
                p = p.left;
            } else if (val > p.val) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    //找最小值
    private Node minimum(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }
}
