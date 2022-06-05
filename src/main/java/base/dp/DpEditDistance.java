package base.dp;

/**
 * @author caotc
 * @date 2022-05-01
 * @since 1.0.0
 */
public class DpEditDistance implements EditDistance{
    //时间复杂度O(word1.length*word2.length)
    @Override
    public int minEditDistance(String word1, String word2, int insertCost, int deleteCost, int replaceCost, int retainCost) {
        //word1前row个字符和word2前column个字符的最小编辑距离
        int[][] minEditDistance=new int[word1.length()+1][word2.length()+1];
        //word1空串时
        for(int word2Length=0;word2Length<word2.length()+1;word2Length++){
            minEditDistance[0][word2Length]=word2Length*insertCost;
        }
        //word2空串时
        for(int word1Length=0;word1Length<word1.length()+1;word1Length++){
            minEditDistance[word1Length][0]=word1Length*deleteCost;
        }
        for(int word1Length=1;word1Length<word1.length()+1;word1Length++){
            for(int word2Length=1;word2Length<word2.length()+1;word2Length++){
                int insertLastCharCost=insertCost+minEditDistance[word1Length][word2Length-1];
                int deleteLastCharCost=deleteCost+minEditDistance[word1Length-1][word2Length];
                minEditDistance[word1Length][word2Length]=Math.min(insertLastCharCost,deleteLastCharCost);
                int replaceOrRetainCost=(word1.charAt(word1Length-1)==word2.charAt(word2Length-1)?retainCost:replaceCost)+minEditDistance[word1Length-1][word2Length-1];
                minEditDistance[word1Length][word2Length]=Math.min(minEditDistance[word1Length][word2Length],replaceOrRetainCost);
            }
        }
        return minEditDistance[word1.length()][word2.length()];
    }
}
