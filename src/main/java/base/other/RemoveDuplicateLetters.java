package base.other;

/**
 * https://leetcode-cn.com/problems/remove-duplicate-letters/
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 * @author caotc
 * @date 2022-05-04
 * @since 1.0.0
 */
public interface RemoveDuplicateLetters {
    /**
     * 去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）
     * @param s 由小写英文字母组成字符串
     * @return 字典序最小的所有字符只保留一个字符串
     */
    String removeDuplicateLetters(String s);
}
