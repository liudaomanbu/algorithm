package base.dp;

/**
 * @author caotc
 * @date 2022-05-01
 * @since 1.0.0
 */
public class DpEditDistance2 implements EditDistance {
    //时间复杂度O(word1.length*word2.length),空间压缩
    @Override
    public int minEditDistance(String word1, String word2, int insertCost, int deleteCost, int replaceCost, int retainCost) {
        if (word1.length() == 0) {
            return word2.length() * insertCost;
        }
        if (word2.length() == 0) {
            return word1.length() * deleteCost;
        }
        //word1前row个字符和word2前column个字符的最小编辑距离
        int[] preMinEditDistance = new int[word2.length() + 1];
        int[] curMinEditDistance = new int[word2.length() + 1];

        //word1空串时
        for (int word2Length = 0; word2Length < word2.length() + 1; word2Length++) {
            preMinEditDistance[word2Length] = word2Length * insertCost;
        }

        for (int word1Length = 1; word1Length < word1.length() + 1; word1Length++) {
            //word2空串时
            curMinEditDistance[0] = word1Length * deleteCost;
            for (int word2Length = 1; word2Length < word2.length() + 1; word2Length++) {
                int insertLastCharCost = insertCost + curMinEditDistance[word2Length - 1];
                int deleteLastCharCost = deleteCost + preMinEditDistance[word2Length];
                curMinEditDistance[word2Length] = Math.min(insertLastCharCost, deleteLastCharCost);
                int replaceOrRetainCost = (word1.charAt(word1Length - 1) == word2.charAt(word2Length - 1) ? retainCost : replaceCost) + preMinEditDistance[word2Length - 1];
                curMinEditDistance[word2Length] = Math.min(curMinEditDistance[word2Length], replaceOrRetainCost);
            }
            if (word1Length != word1.length()) {
                int[] temp = preMinEditDistance;
                preMinEditDistance = curMinEditDistance;
                curMinEditDistance = temp;
            }
        }
        return curMinEditDistance[word2.length()];
    }
}
