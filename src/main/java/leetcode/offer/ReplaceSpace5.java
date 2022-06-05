package leetcode.offer;

/**
 * @author caotc
 * @date 2021-04-21
 * @since 1.0.0
 */
public class ReplaceSpace5 {
    public static void main(String[] args) {
        System.out.println(replaceSpace1("We are happy."));
    }
    public static String replaceSpace1(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb=new StringBuilder();
        for(char c:chars){
            if(c==' '){
                sb.append("%20");
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
