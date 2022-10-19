import java.util.Arrays;

class Solution {
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
