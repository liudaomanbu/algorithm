package base.other;

/**
 * @author caotc
 * @date 2022-05-04
 * @since 1.0.0
 */
public class RemoveDuplicateLetters1 implements RemoveDuplicateLetters{
    @Override
    public String removeDuplicateLetters(String s) {
        if(s==null || s.isEmpty()){
            return s;
        }
        char[] chars = s.toCharArray();
        int[] letterCountMap=letterCountMap(chars);
        int minAsciiIndex=0;
        for(int i=0;i<chars.length;i++){
            if(chars[i]<chars[minAsciiIndex]){
                minAsciiIndex=i;
            }
            if(--letterCountMap[chars[i]-'a']==0){
                break;
            }
        }
        return chars[minAsciiIndex]
                +removeDuplicateLetters(s.substring(minAsciiIndex+1).replaceAll(String.valueOf(chars[minAsciiIndex]),""));
    }

    private int[] letterCountMap(char[] chars){
        int[] letterCountMap=new int[26];
        for(char c:chars){
            letterCountMap[c-'a']+=1;
        }
        return letterCountMap;
    }
}
