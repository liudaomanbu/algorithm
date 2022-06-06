package base.other;

/**
 * @author caotc
 * @date 2022-05-02
 * @since 1.0.0
 */
public class RecursionJosephus implements Josephus {
    @Override
    public int getLive(int n, int m) {
        //1.编号number和计数m的公式为number=(m-1)%n+1
        //2.杀之前编号beforeKillNumber=(afterKillNumber-1+killNumber)%n+1
        //=(afterKillNumber-1+(m-1)%n+1)%n+1
        //=(afterKillNumber+(m-1)%n)%n+1
        //=(afterKillNumber+m-1)%n+1
        //3.最后一轮,只剩1个数时,存活数编号必为1,循环递推至首轮即可直到首轮对应编号.
        if (n == 1) {
            return 1;
        }
        int afterKillNumber = getLive(n - 1, m);
        return (afterKillNumber + m - 1) % n + 1;
    }
}
