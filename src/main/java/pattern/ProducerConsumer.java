package pattern;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author caotc
 * @date 2021-04-12
 * @since 1.0.0
 */
public class ProducerConsumer {
    @Slf4j
    @AllArgsConstructor
    static class Producer implements Runnable{
        BlockingDeque<String> queue;

        public void product(String message){
            log.info("id:{},total:{},product message:{}",Thread.currentThread().getName(),queue.size(),message);
            try {
                queue.put(message);
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error("product error",e);
            }
        }

        @Override
        public void run() {
            while (true){
                String value = new Random().nextInt() + "";
                product(value);
            }
        }
    }
    @Slf4j
    @AllArgsConstructor
    static class Consumer implements Runnable{
        BlockingDeque<String> queue;

        public void consume(){
            try {
                String message = queue.take();
                log.info("id:{},total:{},consume message:{}",Thread.currentThread().getName(),queue.size(),message);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error("consume error",e);
            }
        }

        @Override
        public void run() {
            while (true){
                consume();
            }
        }
    }

    public static void main(String[] args) {
        BlockingDeque<String> queue=new LinkedBlockingDeque<>(100);
        ProducerConsumer.Producer producer = new ProducerConsumer.Producer(queue);
        ProducerConsumer.Consumer consumer = new ProducerConsumer.Consumer(queue);
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 5; i++) {
            executorService.execute(producer);
        }
        executorService.execute(consumer);
    }
}
