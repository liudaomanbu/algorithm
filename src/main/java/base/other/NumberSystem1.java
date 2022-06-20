package base.other;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caotc
 * @date 2022-05-03
 * @since 1.0.0
 */
public class NumberSystem1 implements NumberSystem {
    @Override
    public String transferToOther(int number, int k, char[] numberSystemChars) {
        //1.k进制下从右到左每一位数(n)代表的实际值为value* k^n(从0开始)
        //2.k进制换算时,先处理符号位
        //3.然后从左往右遍历位数,该位数表达大小范围内[k^n,(k-1)*k^n]
        //如果当前剩余number>k^n,则尝试取小于number的maxN,然后number减去maxN
        //4.直到遍历结束,得到k进制下[0,k-1]数字表达的值
        //5.根据每个数字对应的字符进行映射转换
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

    @Override
    public long transferToTen(String number, int k, char[] numberSystemChars) {
        Map<Character,Integer> charToNumber=new HashMap<>();
        for(int i=0;i<numberSystemChars.length;i++){
            charToNumber.put(numberSystemChars[i],i);
        }
        char[] numberChars=number.toCharArray();
        int result=0;
        for(int i=0;i<numberChars.length;i++){
            char digitNumChar=numberChars[numberChars.length-1-i];
            int digitNum=charToNumber.get(digitNumChar);
            result+=digitNum*Math.pow(k,i);
        }
        return result;
    }
}
