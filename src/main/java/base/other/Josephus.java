package base.other;

/**
 * 1,···,n这n个数字排成一个圆圈，从数字1开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 *
 * @author caotc
 * @date 2022-05-02
 * @since 1.0.0
 */
public interface Josephus {
    /**
     * 1,···,n这n个数字排成一个圆圈，从数字1开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
     *
     * @param n 编号最大值
     * @param m 每次杀的计数
     * @return 剩下的编号
     */
    int getLive(int n, int m);
}
