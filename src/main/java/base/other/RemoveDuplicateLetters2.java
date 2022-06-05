package base.other;

/**
 * @author caotc
 * @date 2022-05-04
 * @since 1.0.0
 */
public class RemoveDuplicateLetters2 implements RemoveDuplicateLetters{
    @Override
    public String removeDuplicateLetters(String s) {
        if(s==null || s.isEmpty()){
            return s;
        }
        char[] chars = s.toCharArray();
        int[] letterCountMap=letterCountMap(chars);
        StringBuilder result=new StringBuilder();
        int minAsciiIndex=-1;
        for(int i=0;i<chars.length;i++){
            //字符已经选取过
            if(letterCountMap[chars[i]-'a']==-1){
                continue;
            }
            if(minAsciiIndex==-1 || chars[i]<chars[minAsciiIndex]){
                minAsciiIndex=i;
            }
            //[0,i]拥有所有字符且[i+1,length-1]缺少1个字符,所以[0,i]是可以任意选取保留字符的最大范围
            if(--letterCountMap[chars[i]-'a']==0){
                //为了字典序最小,选取[0,i]最小字典值的字符
                result.append(chars[minAsciiIndex]);
                //选取过的字符记为-1
                letterCountMap[chars[minAsciiIndex]-'a']=-1;
                //回退指针到minAsciiIndex+1,所以回复[minAsciiIndex+1,i]的词频
                for(int j=i;j>minAsciiIndex;j--){
                    if(letterCountMap[chars[j]-'a']!=-1){
                        letterCountMap[chars[j]-'a']++;
                    }
                }
                i=minAsciiIndex;
                minAsciiIndex=-1;
            }
        }
        return result.toString();
    }

    private int[] letterCountMap(char[] chars){
        int[] letterCountMap=new int[26];
        for(char c:chars){
            letterCountMap[c-'a']+=1;
        }
        return letterCountMap;
    }
}
