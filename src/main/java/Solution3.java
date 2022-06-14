class Solution3 {
    public int[] findDiagonalOrder(int[][] mat) {
        int row = 0, col = 0, row_max = mat.length, col_max = mat[0].length;
        int index = 0, direction = 1;
        int[][] choice = {{1, -1}, {-1, 1}};
        int[] ret = new int[row_max * col_max];
        while (row < row_max && col < col_max) {
            ret[index++] = mat[row][col];
            if (direction == 1 && (row == 0 || col == col_max - 1)) {
                direction = 0;
                if (col < col_max - 1) {
                    col++;
                } else {
                    row++;
                }
            } else if (direction == 0 && (col == 0 || row == row_max - 1)) {
                direction = 1;
                if (row < row_max - 1) {
                    row++;
                } else {
                    col++;
                }
            } else {
                row += choice[direction][0];
                col += choice[direction][1];
            }
        }
        return ret;
    }
}
