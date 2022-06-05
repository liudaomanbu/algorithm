package base.other;

/**
 * 进制转换
 * @author caotc
 * @date 2022-05-03
 * @since 1.0.0
 */
public interface NumberSystem {
    char[] SIXTEEN_NUMBER_SYSTEM_CHARS =new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    /**
     * 转换进制
     * @param number 需要转换的十进制数
     * @param k 进制
     * @param numberSystemChars 表达k进制的字符
     * @return k进制下表达字符
     */
     String transfer(int number,int k,char[] numberSystemChars);

     default String transfer(int number,int k){
         if(k==10){
             return String.valueOf(number);
         }
         if(k>10 && k!=16){
             throw new UnsupportedOperationException("need numberSystemChars");
         }
         return transfer(number,k, SIXTEEN_NUMBER_SYSTEM_CHARS);
     }
}
