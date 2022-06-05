package base.string;

/**
 * @author caotc
 * @date 2022-05-10
 * @since 1.0.0
 */
public class MaxPalindromeManacherTest extends MaxPalindromeTest {
    @Override
    protected MaxPalindrome instance() {
        return new MaxPalindromeManacher();
    }
}
