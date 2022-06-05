package base.dp;

/**
 * https://leetcode-cn.com/problems/edit-distance/
 * 给定两个字符串word1,word2,每个字符的插入代价为insertCost,删除代价为deleteCost,替换代价为replaceCost,保留代价为retainCost
 * 返回word1和word2的最短编辑距离
 * @author caotc
 * @date 2022-05-01
 * @since 1.0.0
 */
public interface EditDistance {
    /**
     * 计算最小编辑距离
     * @param word1 字符串1
     * @param word2 字符串2
     * @param insertCost 插入代价
     * @param deleteCost 删除代价
     * @param replaceCost 替换代价
     * @param retainCost 保留代价
     * @return 最小编辑距离
     */
    int minEditDistance(String word1,String word2,int insertCost,int deleteCost,int replaceCost,int retainCost);
}
