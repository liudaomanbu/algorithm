package base.other;

/**
 * @author caotc
 * @date 2022-05-02
 * @since 1.0.0
 */
public class RecursionJosephus implements Josephus {
    @Override
    public int getLive(int n, int m) {
        if (n == 1) {
            return 1;
        }
        return (getLive(n - 1, m) + m - 1) % n + 1;
    }
}
