package base.dp;

import base.dp.DpBag;
import base.dp.RecursionBag;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

@Slf4j
public class BagTest {
    @Value
    static class Info {
        //重量
        int[] weights;
        //价值
        int[] values;
        //背包容量
        int bagSize;
    }

    static class InfoFactory {
        public static Info generate(int goodsMaxSize, int maxWeight, int maxValue, int maxBagSize) {
            Random random = new Random();
            int goodsSize = random.nextInt(goodsMaxSize);
            int[] weight = new int[goodsSize];
            int[] value = new int[goodsSize];
            for (int i = 0; i < goodsSize; i++) {
                weight[i] = random.nextInt(maxWeight);
                value[i] = random.nextInt(maxValue);
            }
            return new Info(weight, value, random.nextInt(maxBagSize));
        }
    }


    @RepeatedTest(1000)
    public void test() {
        Info bagInfo = InfoFactory.generate(50, 100, 100, 200);
        DpBag dpBag = new DpBag();
        int result = dpBag.maxValue(bagInfo.weights, bagInfo.values, bagInfo.bagSize);
        log.debug("bagInfo:{},result:{}", bagInfo, result);
        int maxValue = new RecursionBag().maxValue(bagInfo.weights, bagInfo.values, bagInfo.bagSize);
        if (result != maxValue) {
            log.error("bagInfo:{},maxValue:{},result:{}", bagInfo, maxValue, result);
        }
    }

}