package base.string;

/**
 *
 * @author caotc
 * @date 2022-05-10
 * @since 1.0.0
 */
public interface IndexOf {
    /**
     * 判断childStr是否是str的子串,并返回起始下标.
     * 如果不是返回-1
     * @param str 查找的字符串
     * @param childStr 子串
     * @return 起始下标
     */
    int indexOf(String str,String childStr);
}
