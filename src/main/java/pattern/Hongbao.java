package pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author caotc
 * @date 2021-04-12
 * @since 1.0.0
 */
public class Hongbao {
    public static void main(String[] args) {
        List<Integer> result = generatePacketsByDoubleMean(10, 100);
        System.out.println(result);
    }
   static List<Integer> generatePacketsByDoubleMean(int people, int money) {
        List<Integer> packets = new ArrayList<>();
        Random random = new Random();
        while (people > 1) {
            int p = random.nextInt(2 * money / people);
            packets.add(p);
            money -= p;
            people--;
        }
        packets.add(money);
        return packets;
    }
}
