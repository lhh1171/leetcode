import java.util.Arrays;

/*
* 学校的自助午餐提供圆形和方形的三明治，分别用数字 0 和 1 表示。所有学生站在一个队列里，每个学生要么喜欢圆形的要么喜欢方形的。
* 餐厅里三明治的数量与学生的数量相同。所有三明治都放在一个 栈 里，每一轮：
* 如果队列最前面的学生 喜欢 栈顶的三明治，那么会 拿走它 并离开队列。
* 否则，这名学生会 放弃这个三明治 并回到队列的尾部。
* 这个过程会一直持续到队列里所有学生都不喜欢栈顶的三明治为止。
* 给你两个整数数组 students 和 sandwiches ，其中 sandwiches[i] 是栈里面第 i 个三明治的类型（i = 0 是栈的顶部），
* students[j] 是初始队列里第 j 名学生对三明治的喜好（j = 0 是队列的最开始位置）。请你返回无法吃午餐的学生数量。
*
* */
class Solution11 {
    public int countStudents(int[] students, int[] sandwiches) {
        //当后面的学生喜欢吃的都一样，但是顶的三明治不一样，返回后面的数量

        //有多少个一
        int s1 = Arrays.stream(students).sum();
        //有多少个零
        int s0 = students.length - s1;

        //遍历三明治
        for (int sandwich : sandwiches) {
            if (sandwich == 0 && s0 > 0) {
                //当栈顶是0
                s0--;
            } else if (sandwich == 1 && s1 > 0) {
                //当栈顶是1
                s1--;
            } else {
                break;
            }
        }
        return s0 + s1;
    }
}
