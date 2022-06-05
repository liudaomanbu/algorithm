package base.other;

/**
 * 约瑟夫环问题
 * https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 *
 * @author caotc
 * @date 2022-05-02
 * @since 1.0.0
 */
public class Josephus1 implements Josephus {

    public int getLive(int n, int m) {
        int number = 1;
        for (int round = 1; round <= n; round++) {
            number = getBeforeKillNumber(number, getKillNumber(round, m), round);
        }
        return number;
    }

    private int getKillNumber(int n, int m) {
        return (m - 1) % n + 1;
    }

    private int getBeforeKillNumber(int afterKillNumber, int killNumber, int n) {
        return (afterKillNumber - 1 + killNumber) % n + 1;
    }
}
