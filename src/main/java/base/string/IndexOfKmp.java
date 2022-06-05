package base.string;

/**
 * @author caotc
 * @date 2022-05-10
 * @since 1.0.0
 */
public class IndexOfKmp implements IndexOf {
    @Override
    public int indexOf(String str, String childStr) {
        if (str == null || childStr == null || childStr.length() > str.length()) {
            return -1;
        }
        char[] string = str.toCharArray();
        char[] childString = childStr.toCharArray();
        int[] maxPrefixSuffixLengths = maxPrefixSuffixLengths(childStr.toCharArray());
        int i = 0;
        int j = 0;
        while (i < string.length && j < childString.length) {
            if (string[i] == childString[j]) {
                i++;
                j++;
            } else if (j > 0) {
                j = maxPrefixSuffixLengths[j];
            } else {
                i++;
            }
        }
        return j == childString.length ? i - j : -1;
    }

    /**
     * 计算传入字符数组每个字符对应的之前所有字符的前缀字符串和后缀字符串的最长相同长度
     *
     * @param str 字符数组
     * @return 前缀字符串和后缀字符串的最长相同长度数组
     */
    private int[] maxPrefixSuffixLengths(char[] str) {
        if (str.length < 2) {
            return new int[]{0, 0};
        }
        //maxPrefixSuffixLengths[i]代表[0,i-2]和[1,i-1]的最长相同长度
        int[] maxPrefixSuffixLengths = new int[str.length];
        //i为0,1时都不存在最长相同长度
        maxPrefixSuffixLengths[0] = 0;
        maxPrefixSuffixLengths[1] = 0;
        int i = 2;
        int pos = 0;
        while (i < maxPrefixSuffixLengths.length) {
            if (str[i - 1] == str[pos]) {//[0,pos]和[i-1-pos,i-1]相同
                maxPrefixSuffixLengths[i++] = ++pos;
            } else if (pos > 0) {
                pos = maxPrefixSuffixLengths[pos];
            } else {
                i++;
            }
        }
        return maxPrefixSuffixLengths;
    }
}

