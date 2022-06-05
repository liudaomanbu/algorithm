package base.other;

/**
 * 约瑟夫环问题
 * https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 *
 * @author caotc
 * @date 2022-05-02
 * @since 1.0.0
 */
public class Josephus2 implements Josephus {

    public int getLive(int n, int m) {
        int number = 1;
        for (int round = 1; round <= n; round++) {
            number = (number + m - 1) % round + 1;
        }
        return number;
    }

}
