package pattern;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.jupiter.api.Assertions.*;

class ProducerConsumerTest {
    @Test
    public void test (){
        BlockingDeque<String> queue=new LinkedBlockingDeque<>(100);
        ProducerConsumer.Producer producer = new ProducerConsumer.Producer(queue);
        ProducerConsumer.Consumer consumer = new ProducerConsumer.Consumer(queue);
        for (int i = 0; i < 5; i++) {
            new Thread(producer, "Producer" + (i + 1)).start();
            new Thread(consumer, "Consumer" + ( 1)).start();
        }

    }
}