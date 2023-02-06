package 最old;

import java.util.ArrayList;
import java.util.List;

class Solution7 {
    static int MOD = (int)1e9+7;
    static List<Integer> list = new ArrayList<>();
    static {
        for (int i = 2; i <= 100; i++) {
            boolean ok = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) ok = false;
            }
            if (ok) list.add(i);
        }
    }
    public int numPrimeArrangements(int n) {
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (list.get(mid) <= n) l = mid;
            else r = mid - 1;
        }
        int a = r + 1, b = n - a;
        long ans = 1;
        for (int i = b; i > 1; i--) ans = ans * i % MOD ;
        for (int i = a; i > 1; i--) ans = ans * i % MOD ;
        return (int)ans;
    }
}
/*
class demo{
    public static void main(String[] args) {
        old.Solution7 solution7=new old.Solution7();
        System.out.println(solution7.numPrimeArrangements(3));

    }
}*/
