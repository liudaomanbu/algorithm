package base.dp;

import lombok.extern.slf4j.Slf4j;

/**
 * @author caotc
 * @date 2022-05-01
 * @since 1.0.0
 */
@Slf4j
public class RecursionEditDistance implements EditDistance{
    @Override
    public int minEditDistance(String word1, String word2, int insertCost, int deleteCost, int replaceCost, int retainCost) {
        return minEditDistance(word1,word2,insertCost,deleteCost,replaceCost,retainCost,word1.length(),word2.length());
    }

    /**
     * 计算最小编辑距离
     * @param word1 字符串1
     * @param word2 字符串2
     * @param insertCost 插入代价
     * @param deleteCost 删除代价
     * @param replaceCost 替换代价
     * @param retainCost 保留代价
     * @param word1Length word1计算长度
     * @param word2Length word2计算长度
     * @return 最小编辑距离
     */
    public int minEditDistance(String word1, String word2, int insertCost, int deleteCost, int replaceCost, int retainCost,int word1Length,int word2Length) {
        if(word1Length==0){
            return word2Length*insertCost;
        }
        if(word2Length==0){
            return word1Length*deleteCost;
        }
        int insertLastCharCost=insertCost+minEditDistance(word1,word2,insertCost,deleteCost,replaceCost,retainCost,word1Length,word2Length-1);
        int deleteLastCharCost=deleteCost+minEditDistance(word1,word2,insertCost,deleteCost,replaceCost,retainCost,word1Length-1,word2Length);
        int minEditDistance=Math.min(insertLastCharCost,deleteLastCharCost);
        int replaceOrRetainCost=(word1.charAt(word1Length-1)==word2.charAt(word2Length-1)?retainCost:replaceCost)+minEditDistance(word1,word2,insertCost,deleteCost,replaceCost,retainCost,word1Length-1,word2Length-1);
        return Math.min(minEditDistance,replaceOrRetainCost);
    }
}
