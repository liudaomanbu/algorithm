package base.other;

import base.other.Shuffle;
import base.other.Shuffle1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;
import java.util.Random;

@Slf4j
public class ShuffleTest {
    @RepeatedTest(1000)
    public void test(){
        Random random=new Random();
        String[] array=new String[random.nextInt(10)*2];
        for(int i=0;i<array.length/2;i++){
            array[i]="A"+(i+1);
            array[array.length/2+i]="B"+(i+1);
        }
        log.debug("array:{}",Arrays.toString(array));

        Shuffle shuffle=new Shuffle1();
        shuffle.shuffle(array);
        for(int i=0;i<array.length;i++){
            int count=i/2+1;
            Assertions.assertEquals((i%2==0?"B":"A")+count,array[i],Arrays.toString(array));
        }
        log.debug("array:{}",Arrays.toString(array));
    }

    @RepeatedTest(1000)
    public void test1(){
        Random random=new Random();
        int[] array=new int[random.nextInt(10)*2];
        for(int i=0;i<array.length/2;i++){
            array[i]=i+1;
            array[array.length/2+i]=i+1;
        }
        log.debug("array:{}",Arrays.toString(array));

        Shuffle shuffle=new Shuffle1();
        shuffle.shuffle(array);
        for(int i=0;i<array.length;i++){
            int count=i/2+1;
            Assertions.assertEquals((i%2==0?"B":"A")+count,array[i],Arrays.toString(array));
        }
        log.debug("array:{}",Arrays.toString(array));
    }
}