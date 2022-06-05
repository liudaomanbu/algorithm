package base.dp;

import base.dp.DpEditDistance;
import base.dp.DpEditDistance2;
import base.dp.RecursionEditDistance;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

@Slf4j
public class EditDistanceTest {
    @Value
    static class Info {
        String word1;
        String word2;
        int insertCost;
        int deleteCost;
        int replaceCost;
        int retainCost;
    }

    static class InfoFactory {
        public static Info generate(int maxLength, int maxCost) {
            Random random = new Random();
            char[] word1=new char[random.nextInt(maxLength)];
            for(int i=0;i<word1.length;i++){
                word1[i]= (char) (random.nextInt(26)+'A');
            }
            char[] word2=new char[random.nextInt(maxLength)];
            for(int i=0;i<word2.length;i++){
                word2[i]= (char) (random.nextInt(26)+'A');
            }
            return new Info(String.valueOf(word1),String.valueOf(word2),random.nextInt(maxCost),random.nextInt(maxCost),random.nextInt(maxCost),random.nextInt(maxCost));
        }
    }


    @RepeatedTest(1000)
    public void test() {
        Info info = InfoFactory.generate(10,  10);
        DpEditDistance dpEditDistance = new DpEditDistance();
        int result = dpEditDistance.minEditDistance(info.word1, info.word2, info.insertCost,info.deleteCost,info.replaceCost,info.retainCost);
        log.debug("info:{},result:{}", info, result);
        int minEditDistance = new RecursionEditDistance().minEditDistance(info.word1, info.word2, info.insertCost,info.deleteCost,info.replaceCost,info.retainCost);
        Assertions.assertEquals(minEditDistance,result,String.format("info:%s,minEditDistance:%s,result:%s", info, minEditDistance, result));
    }

    @RepeatedTest(1000)
    public void test2() {
        Info info = InfoFactory.generate(10,  10);
        DpEditDistance2 dpEditDistance = new DpEditDistance2();
        int result = dpEditDistance.minEditDistance(info.word1, info.word2, info.insertCost,info.deleteCost,info.replaceCost,info.retainCost);
        log.debug("info:{},result:{}", info, result);
        int minEditDistance = new RecursionEditDistance().minEditDistance(info.word1, info.word2, info.insertCost,info.deleteCost,info.replaceCost,info.retainCost);
        Assertions.assertEquals(minEditDistance,result,String.format("info:%s,minEditDistance:%s,result:%s", info, minEditDistance, result));
    }
}