package base.dp;

/**
 * 一个数列：1、1、2、3、5、8、13、21、34
 * F(0)=0，F(1)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 2，n ∈ N*）
 * @author caotc
 * @date 2022-05-04
 * @since 1.0.0
 */
public interface Fibonacci {
    int fibonacci(int n);
}
