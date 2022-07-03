import java.util.Arrays;

//i从最后一个数字下标起，往前找，找到nums[i]<nums[i+1]这样的下标i，找不到就代表数字是54321这样的，return -1
//找到了，然后把nums[i] 替换成 它的右边 比它大的所有数字中<最小的那个数字>
//比如230241 ,发现了2<4，i定为2对应下标，然后把他替换成2右边>2的数字中最小的那个，41中只有4，-->230421
//再把替换后的数组，i下标位置后的数排个序，达到最小的效果 4右边的21排序，成12，最终-->230412得最优
 class Solution9 {
    public int nextGreaterElement(int n) {
        //只有一位数，那没有组合能大于它自己
        if (n < 10)
            return -1;

        //将整数n转成由整数组成的字符串，101 --> "101"
        String str = String.valueOf(n);

        // //如果是两位数，只有交换十位和个位一种做法(即交换整数下标0 和 下标1位置的数字)
        if (n < 100) {
            //将数字字符串str转化成int数组，每个数组元素 对应一个 (字符对应的数字)，比如将"123"转成了整型数组[1,2,3]
            int[] number = new int[str.length()];
            for (int k = 0; k < str.length(); k++) {
                //先由字符串转换成char,再转换成String,然后Integer
                number[k] = Integer.parseInt(String.valueOf(str.charAt(k)));
            }
            //交换十位和个位
            int temp = number[0];
            number[0] = number[1];
            number[1] = temp;

            //再将转换好的int数组转回字符串
            StringBuffer sb = new StringBuffer();
            for (int k = 0; k < str.length(); k++) {
                sb.append(String.valueOf(number[k]));
            }

            int x = Integer.parseInt(sb.toString());
            return x > n ? x : -1;
        }

        //如果是三位数以上，从个位数向前找一个比当个位小的数，交换位置
        //如12345，从5开始向前找到第一个比5小的数4，交换位置得到最小的大于12345的数12354
        //如果个位找不到，就换从十位向前找，如12341，个位1找不到了，就从十位4往前找，发现大于百位3，交换位置得到12431
        //十位找不到就从百位往前找，以此类推，直到找不到
        if (n > 100) {

            //将得到的字符串str转化成int数组，每个数组元素 对应一个 (字符对应的数字)，比如将"101"转成了整型数组[1,0,1]
            int[] number = new int[str.length()];
            for (int k = 0; k < str.length(); k++) {
                //先由字符串转换成char,再转换成String,然后Integer
                number[k] = Integer.parseInt(String.valueOf(str.charAt(k)));
            }
            //记录交换位置的数字下标
            int index = -1;
            for (int i = number.length - 1; i > 0; i--) {
                //从最后一个数字(个位起)往前找比他小的数字位置，找不到换十位、百位起往前找....
                for (int j = i - 1; j >= 0; j--) {
                    //交换位置
                    if (number[i] > number[j]) {
                        int temp = number[i];
                        number[i] = number[j];
                        number[j] = temp;
                        index = j;
                        break;
                    }
                }
                if (index != -1)
                    break;
            }

            //如54321，这样的是找不到的，直接返回-1
            if (index == -1)
                return -1;
            else {
                //不能直接交换完就返回这个数组对应的数字
                //因为测试用例发现123 54 -->124 53,而最优是124 35
                //说明把4和3交换了以后，4以后的所有数字，正序排列才是最小，即还需要把4后面的53排个序成35
                //比如123 987654-->124 987653还需要排序-->124 356789

                //把index之后的数字排个序
                Arrays.sort(number, index + 1, number.length);
                //最后将弄好的int数组转化成字符串

                StringBuffer sb = new StringBuffer();
                for (int k = 0; k < str.length(); k++) {
                    sb.append(String.valueOf(number[k]));
                }

                return Integer.parseInt(sb.toString());
            }

        }
        return -1;
    }
}
