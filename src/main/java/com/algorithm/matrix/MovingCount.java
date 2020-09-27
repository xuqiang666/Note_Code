package com.algorithm.matrix;

/**
 * @Author: 许庆之 on 2020/9/27.
 *
 * 广度优先搜索， 13机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 */
public class MovingCount {

    public int movingCount(int m, int n, int k) {
        if(m<=0||n<=0||k<0) {return -1;}
        if(k==0){return 1;}
        // 默认都为false,当经过此节点置为true
        boolean[][] board = new boolean[m][n];
        int res = bfs(0,0,m,n,k,board);
        return res;
    }

    private int bfs(int i,int j,int m,int n,int k,boolean[][] board){
        if (i < 0 || j < 0 || i == board.length || j == board[0].length || moveable(i,j,k) || board[i][j]) {
            return 0;
        }
        //res++;
        board[i][j]=true;
        /**其实只要往右或者往下就行了！节约时间
         * return 1+bfs(i+1,j,m,n,k,board)+bfs(i,j+1,m,n,k,board)
         */
        return 1+bfs(i+1,j,m,n,k,board)+bfs(i,j+1,m,n,k,board);
                //+bfs(i-1,j,m,n,k,board)+bfs(i,j-1,m,n,k,board);
    }

    /** 对本题i,j<100  为节约时间
     * 直接 return i%10+i/10+j%10+j/10>k;
     */
    private boolean moveable(int i,int j,int k){
        return count(i) + count(j) > k;
    }

    private int count(int n){
        String a = n + "";
        int len = a.length();
        String[] c = new String[len];
        for(int i=0;i<len;i++){
            c[i] = a.substring(i,i+1);
        }
        int sum=0;
        for(int i=0;i<len;i++){
            sum += Integer.parseInt(c[i]);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new MovingCount().movingCount(3,2,1));
    }
}
