package pattern;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author caotc
 * @date 2021-04-12
 * @since 1.0.0
 */
public class ThreadPrint3 {
    private static  Semaphore[] semaphores;
    /**
     * 当前即将打印的数字
     */
    private static volatile int current = 0;

    public static class Printer implements Runnable {
        /**
         * 当前线程编号，从0开始
         */
        private int threadNo;
        /**
         * 线程数量
         */
        private int threadCount;
        /**
         * 打印的最大数值
         */
        private int maxInt;

        public Printer(int threadNo, int threadCount, int maxInt) {
            this.threadNo = threadNo;
            this.threadCount = threadCount;
            this.maxInt = maxInt;
        }

        @Override
        public void run() {
            while (current<=maxInt) {
                try {
                    semaphores[threadNo].acquire();
                    System.out.println("thread" + threadNo + " : " + current);
                    current++;
                    if(current<=maxInt){
                        semaphores[threadNo==(threadCount-1)?0:threadNo+1].release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static void main(String[] args) {
        int threadCount=3;
        int max = 100;
        semaphores=new Semaphore[threadCount];
        semaphores[0]=new Semaphore(1,true);
        for(int i=1;i<threadCount;i++){
            semaphores[i]=new Semaphore(0,true);
        }
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Printer(i, threadCount, max)).start();
        }

    }
}
