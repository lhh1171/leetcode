package Bycategory.数组;


public class Solution59 {
    public int[][] generateMatrix(int n){
        int[][] nums=new int[n][n];
        int startX=0,startY=0,offset=1,count=1;
        int i=0,j=0;
        int loop=n/2;
        while (loop--!=0){
            for (j=startY;j<n-offset;j++){
                nums[startX][j]=count++;
            }
            for (i=startX;i<n-offset;i++){
                nums[i][j]=count++;
            }
            for (;j>startY;j--){
                nums[i][j]=count++;
            }
            for (;i>startX;i--){
                nums[i][j]=count++;
            }
            startX++;
            startY++;
            offset++;
        }
        //如果是奇数的的话最后一次的复制
        if (n%2==1){
            nums[i][j]=count;
        }
        return nums;
    }

    public static void main(String[] args) {
        Solution59 solution59=new Solution59();
        int n=7;
        int[][] nums = solution59.generateMatrix(7);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d\t",nums[i][j]);
            }
            System.out.println();
        }
    }
}
