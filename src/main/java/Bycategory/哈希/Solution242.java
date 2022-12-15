package Bycategory.哈希;

import java.util.HashMap;
import java.util.Map;

public class Solution242 {
    //数组法类hash
    public static boolean isAnagram1(String s, String t) {
        int[] record = new int[26];
        for (char c : s.toCharArray()) {
            record[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            record[c - 'a']--;
        }
        for (int i : record) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    //哈希法
    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> table = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            table.put(c, table.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            table.put(c, table.getOrDefault(c, 0) - 1);
            if (table.get(c) < 0) {
                return false;
            }
        }
        return true;
    }

    //包含unicode的情况,哈希法
    //UNICODE是超大字符集，包含2^21（2097152）个字符的统一定义。
    // UTF-8是对UNICODE的一种编码方式，它以变长字节的方式编码UNICODE字符
    /*
    *
    * UTF-8 的编码规则很简单，只有二条：

        1）对于单字节的符号，字节的第一位设为0，后面7位为这个符号的 Unicode 码。因此对于英语字母，UTF-8 编码和 ASCII 码是相同的。

        2）对于n字节的符号（n > 1），第一个字节的前n位都设为1，第n + 1位设为0，后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的 Unicode 码。

        下表总结了编码规则，字母x表示可用编码的位。

        Unicode符号范围     |        UTF-8编码方式
        (十六进制)        |              （二进制）
        ----------------------+---------------------------------------------
        0000 0000-0000 007F | 0xxxxxxx
        0000 0080-0000 07FF | 110xxxxx 10xxxxxx
        0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
        0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
    *
    *
    *跟据上表，解读 UTF-8 编码非常简单。如果一个字节的第一位是0，则这个字节单独就是一个字符；
    * 如果第一位是1，则连续有多少个1，就表示当前字符占用多少个字节。
    * 下面，还是以汉字严为例，演示如何实现 UTF-8 编码。
    * 严的 Unicode 是4E25（100111000100101），根据上表，
    * 可以发现4E25处在第三行的范围内（0000 0800 - 0000 FFFF），
    * 因此严的 UTF-8 编码需要三个字节，即格式是1110xxxx 10xxxxxx 10xxxxxx。
    * 然后，从严的最后一个二进制位开始，依次从后向前填入格式中的x，多出的位补0。
    * 这样就得到了，严的 UTF-8 编码是11100100 10111000 10100101，转换成十六进制就是E4B8A5。
    * */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> table = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) - 1);
            if (table.get(ch) < 0) {
                return false;
            }
        }
        return true;
    }

}
