package com.x.java.matrix;

/**
 * Create By  xqz on 2020/9/16.
 */
public class MatrixExist {
    static class Solution {
        public boolean exist(char[][] board, String word) {
            char[][] bb = new char[board.length+2][board[0].length+2];
            for (int i = 1; i <= board.length; i++) {
                for (int j = 1; j <= board[0].length; j++) {
                    bb[i][j] = board[i-1][j-1];
                }
            }
            int rown = bb.length;
            int coln = bb[0].length;
            for(int i=1;i<rown;i++){
                for(int j=1;j<coln;j++){
                    if(bb[i][j]==word.charAt(0)){
                        //bb[i][j] = ' ';
                        if(resursion(bb,i,j,word.substring(1))){
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        private boolean resursion(char[][] bb,int i,int j,String word){

            bb[i][j] = ' ';
            if(word.length()==1) {return true;}
            char c = word.charAt(0);
            if(bb[i-1][j]==c) { if(resursion(bb,i-1,j,word.substring(1))) {return true;} }
            if(bb[i+1][j]==c) { if(resursion(bb,i+1,j,word.substring(1))) {return true;} }
            if(bb[i][j-1]==c) { if(resursion(bb,i,j-1,word.substring(1))) {return true;} }
            // 咋就不从1，3到1，4 叻
            // 我直接把矩阵的值置空了，当为假时不可逆了！:( 此时1，4 的值已经被访问过已经是空了！，所以不会在1，3的位置往1，4走了
            if(bb[i][j+1]==c) { if(resursion(bb,i,j+1,word.substring(1))) {return true;} }
            return false;
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        String word = "ABCESEEEFS";
        char[][] board2 = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word2 = "ABCCED";
        // 通不过啊
        boolean y = new Solution().exist(board,word);
        // 这就能通过了
        boolean x = new Solution2().exist(board,word);
        System.out.println(x);
    }

    static class Solution2 {
        public boolean exist(char[][] board, String word) {
            if (board == null || board[0] == null || board.length == 0 || board[0].length == 0 || word == null || word.equals("")) {
                return false;
            }
            boolean[][] isVisited = new boolean[board.length][board[0].length];
            char[] chs = word.toCharArray();

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == chs[0]) {
                        if (bfs(board, i, j, isVisited, chs, 0)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private boolean bfs(char[][] board, int i, int j, boolean[][] isVisited, char[] chs, int index) {

            if (index == chs.length) {
                return true;
            }
            if (i < 0 || j < 0 || i == board.length || j == board[0].length || isVisited[i][j] || board[i][j] != chs[index]) {
                return false;
            }
            isVisited[i][j] = true;
            boolean res = bfs(board, i + 1, j, isVisited, chs, index + 1)
                    || bfs(board, i - 1, j, isVisited, chs, index + 1)
                    || bfs(board, i, j + 1, isVisited, chs, index + 1)
                    || bfs(board, i, j - 1, isVisited, chs, index + 1);
            //当res为假时取消标记  上面的解法就少考虑了这个地方
            isVisited[i][j] = false;
            return res;
        }
    }
}
