class Solution5 {
    public void duplicateZeros(int[] arr) {
        int n = arr.length, i = 0, j = 0;
        while (j < n) {
            //遍历数组当元素等于0的时候，跳过j++，循环过后i的值等于非0个数
            if (arr[i] == 0) j++;
            i++; j++;
        }
        i--; j--;
        //遍历的次数等于非零元素的个数
        while (i >= 0) {
            //将元素右移动
            if (j < n) arr[j] = arr[i];
            //多加零
            if (arr[i] == 0 && --j >= 0) arr[j] = 0;
            i--; j--;
        }
    }
}
