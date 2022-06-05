package base.string;

/**
 * @author caotc
 * @date 2022-05-10
 * @since 1.0.0
 */
public class MaxPalindromeManacher implements MaxPalindrome {
    @Override
    public int maxPalindromeLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] string = addVirtualChar(str);
        int[] maxPalindromeRadii = new int[string.length];
        int maxPalindromeRightIndex = -1;
        int maxPalindromeCenterIndex = -1;
        int maxPalindromeLength = 0;
        for (int i = 0; i < maxPalindromeRadii.length; i++) {
            //i相对于maxPalindromeCenterIndex的对称点
            int j = maxPalindromeCenterIndex * 2 - i;
            //如果i不在已知最大回文串范围内,则从1开始.
            //如果i在已知最大回文串内,且对称点j的回文串整个在最大回文串内,则i的回文串半径和j相同
            //如果i在已知最大回文串内,且对称点j的回文串有一部分在最大回文串外,则i的回文串半径至少到最大回文串右边界maxPalindromeRightIndex
            maxPalindromeRadii[i] = maxPalindromeRightIndex > i ? Math.min(maxPalindromeRadii[j], maxPalindromeRightIndex - i) : 1;
            while (i - maxPalindromeRadii[i] >= 0 && i + maxPalindromeRadii[i] < maxPalindromeRadii.length) {
                if (string[i - maxPalindromeRadii[i]] == string[i + maxPalindromeRadii[i]]) {
                    maxPalindromeRadii[i]++;
                } else {
                    break;
                }
            }
            if (i + maxPalindromeRadii[i] > maxPalindromeRightIndex) {
                maxPalindromeRightIndex = i + maxPalindromeRadii[i];
                maxPalindromeCenterIndex = i;
            }

            maxPalindromeLength = Math.max(maxPalindromeLength, maxPalindromeRadii[i]);
        }

        return maxPalindromeLength - 1;
    }

    /**
     * 每隔一个原始字符就插入一个虚拟字符#
     *
     * @param str 字符串
     * @return 插入虚拟字符后
     */
    private static char[] addVirtualChar(String str) {
        char[] string = str.toCharArray();
        char[] result = new char[str.length() * 2 + 1];
        for (int i = 0; i < string.length; i++) {
            result[i * 2] = '#';
            result[i * 2 + 1] = string[i];
        }
        result[result.length - 1] = '#';
        return result;
    }
}
