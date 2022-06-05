package pattern;

import java.util.concurrent.Semaphore;

/**
 * @author caotc
 * @date 2021-04-12
 * @since 1.0.0
 */
public class MultiThreadsPrintWithSemaphore {
    private static Semaphore s1 = new Semaphore(1, true);
    private static Semaphore s2 = new Semaphore(0, true);
    private static Semaphore s3 = new Semaphore(0, true);
    static volatile int number=0;
    static volatile int maxCount=12;

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            while (number<=maxCount) {
                System.out.println("a:"+number);
                try {
                    s1.acquire();
                    System.out.println("A");
                    number++;
                    s2.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread b = new Thread(() -> {
            while (number<=maxCount) {
                System.out.println("b:"+number);
                try {
                    s2.acquire();
                    System.out.println("B");
                    number++;
                    s3.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread c = new Thread(() -> {
            while (number<=maxCount) {
                System.out.println("c:"+number);
                try {
                    s3.acquire();
                    System.out.println("C");
                    number++;
                    s1.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        a.start();
        b.start();
        c.start();
    }
}
