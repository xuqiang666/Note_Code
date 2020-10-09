package com.algorithm.matrix;

/**
 * Create By  xqz on 2020/9/30.
 * 29.顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 */
public class spiralOrder {
    public int[] spiralOrder(int[][] matrix) {
        if(matrix==null) return null;
        int m = matrix.length;
        if(m==0) return new int[0];
        int n = matrix[0].length;
        // 边界
        int up=0,down=m-1,left=0,right=n-1;
        int c=0;
        int[] res = new int[m*n];
        while(c<m*n){       // 此条件作为退出条件并不能及时生效，需要下面的break语句退出

            for(int k=left;k<=right;k++){
                res[c++] = matrix[up][k];
            }
            if(++up>down) break;    // 必须在每次循环后判断，不然程序不能及时停止

            for(int k=up;k<=down;k++){
                res[c++] = matrix[k][right];
            }
            if(--right<left) break;

            for(int k=right;k>=left;k--){
                res[c++] = matrix[down][k];
            }
            if(--down<up) break;

            for(int k=down;k>=up;k--){
                res[c++] = matrix[k][left];
            }
            if(++left>right) break;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] tes = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        //int[][] tes = {{1,2,3,},{4,5,6},{7,8,9}};
        int[] ints = new spiralOrder().spiralOrder(tes);
        for (int i:ints){
            System.out.print(i+" ");
        }
    }

    /**
     *  标记已走过的路径，按顺时针从右开始遍历方向，一直遍历矩阵
     * */
    public int[] spiralOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int[] order = new int[total];
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order[i] = matrix[row][column];
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }


}
