package leetcode.offer;

/**
 * @author caotc
 * @date 2021-04-23
 * @since 1.0.0
 */
public class IsMatch19 {
    public static void main(String[] args) {
        System.out.println(isMatch2("aa", "a"));
        System.out.println(isMatch2("aa", "a*"));
        System.out.println(isMatch2("ab", ".*"));
        System.out.println(isMatch2("aab", "c*a*b"));
        System.out.println(isMatch2("mississippi", "mis*is*p*"));
        System.out.println(isMatch2("ab", ".*c"));
    }

    public static boolean isMatch(String s, String p) {
        //dp含义为长度而非下标
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                //分成空正则和非空正则两种
                if (j == 0) {
                    dp[i][j] = i == 0;
                } else {
                    //非空正则分为两种情况 * 和 非*
                    char pw = p.charAt(j - 1);
                    if (pw == '*') {
                        //如果dp[i][j - 2]已经为true,那么当前的x*不影响最终结果,直接为true
                        if (j >= 2) {
                            dp[i][j] |= dp[i][j - 2];
                            if (i >= 1) {
                                pw = p.charAt(j - 2);
                                dp[i][j] |= (s.charAt(i - 1) == pw || pw == '.') && dp[i - 1][j];
                            }
                        }
                    } else {
                        dp[i][j] = i > 0 && (s.charAt(i - 1) == pw || pw == '.') && dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static boolean isMatch2(String s, String p) {
        return isMatch2(s, p, s.length() , p.length() );
    }

    public static boolean isMatch2(String s, String p, int i, int j) {
        if (j == 0) {
            return i == 0;
        }
        if(i==0){
            if((j & 1)==1){
                return false;
            }
            return p.charAt(j-1)=='*' && isMatch2(s,p,i,j-2);
        }
        char w = s.charAt(i-1);
        char pw = p.charAt(j-1);
        if (pw == '*') {
            if (j >= 2) {
                return isMatch2(s, p, i, j - 2) || ((p.charAt(j - 2)==w || p.charAt(j - 2)=='.') && isMatch2(s, p, i - 1, j));
            }else{
                return false;
            }
        } else {
            return (w == pw || pw == '.') && isMatch2(s, p, i - 1, j - 1);
        }
    }
}
