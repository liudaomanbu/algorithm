package base.other;

/**
 * @author caotc
 * @date 2022-05-03
 * @since 1.0.0
 */
public class NumberSystem1 implements NumberSystem {
    @Override
    public String transfer(int number, int k, char[] numberSystemChars) {
        int length = 1;
        int actualValue = 1;
        while ((number / k) > actualValue) {
            actualValue *= k;
            length++;
        }

        char[] result=new char[length];
        for(int i=0;i<result.length;i++){
            //当前位的值
            int value=number/actualValue;
            result[i]=numberSystemChars[value];
            number-=value*actualValue;
            actualValue/=k;
        }
        return String.valueOf(result);
    }
}
