package old;

import java.util.ArrayList;
import java.util.List;
/*遍历字符串当遇到运算符时可以将字符串分为两部分分别求解，然后根据当前运算符将两部分结果进行响应的运算即可。
使用递归即可枚举出所有情况。递归的边界条件为子串内没有运算符，子串所代表的数字就是结果。
递归每深入一层，子串里的运算符就提升一级。每层递归里的 for 保证当前子串的每个运算符都在同一优先级运算过。
*/
class Solution8 {
    char[] array;
    public List<Integer> diffWaysToCompute(String expression) {
        array = expression.toCharArray();
        return dfs(0, array.length-1);
    }

    private List<Integer> dfs(int l, int r) {
        List<Integer> ans = new ArrayList<>();
        for(int i = l; i <= r; i++) {
            if(array[i] >= '0' && array[i] <= '9') continue;
            //碰到运算符递归起来,l:0,      i-1:-1,0,1,2,3,4....     i+1:1,2,3,4,5,6,       r:5
            List<Integer> lr = dfs(l, i-1), rr = dfs(i+1, r);
            for(int a: lr) {
                for(int b: rr) {
                    int res = 0;
                    //算数运算符的选择，并且计算结果
                    if(array[i] == '+') {
                        res = a+b;
                        System.out.println(a+"+"+b+"="+res+"====================================");
                    } else if(array[i] == '-') {
                        res = a - b;
                        System.out.println(a+"-"+b+"="+res+"====================================");
                    } else {
                        res = a * b;
                        System.out.println(a+"*"+b+"="+res+"====================================");
                    }
                    //添加一个结果
                    ans.add(res);
                }
            }
        }
        if(ans.isEmpty()) {
            int cur = 0;
            for(int i = l; i <= r; i++) {
                cur = cur*10 + array[i] - '0';
            }
            //添加一个结果
            System.out.println(cur+"------------------------------****************************************----------");
            ans.add(cur);
        }
        System.out.println("result"+ans);
        return ans;
    }
}
//class demo{
//    public static void main(String[] args) {
//        old.Solution8 solution=new old.Solution8();
//        System.out.println(solution.diffWaysToCompute("2*3-4*5"));
//        System.out.println("-------------------------------------------------------");
//    }
//}
/*[-34, -10, -14, -10, 10]*/