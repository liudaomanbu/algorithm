package leetcode.offer;

/**
 * @author caotc
 * @date 2021-04-22
 * @since 1.0.0
 */
public class Exist12 {
    public static void main(String[] args) {
        System.out.println(exist1(new char[][]{
                new char[]{'A','B','C','E'},
                new char[]{'S','F','C','S'},
                new char[]{'A','D','E','E'}
        },"ABCCED"));
    }
    public static boolean exist1(char[][] board, String word) {
        char[] words = word.toCharArray();
        for(int row=0;row<board.length;row++){
            for(int col=0;col<board[0].length;col++){
                if (dfs(board,words,row,col,0)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean dfs(char[][] board, char[] words,int row,int col,int wordIndex){
        if(row <0 || row>=board.length || col<0 || col >=board[0].length || words[wordIndex]!=board[row][col]){
            return false;
        }
        if(wordIndex==words.length-1){
            return true;
        }
        board[row][col]=Character.MIN_VALUE;
        boolean exist=dfs(board,words,row-1,col,wordIndex+1)
                || dfs(board,words,row,col-1,wordIndex+1)
                || dfs(board,words,row+1,col,wordIndex+1)
                || dfs(board,words,row,col+1,wordIndex+1);
        board[row][col]=words[wordIndex];
        return exist;
    }
}
