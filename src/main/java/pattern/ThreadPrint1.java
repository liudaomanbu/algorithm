package pattern;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author caotc
 * @date 2021-04-12
 * @since 1.0.0
 */
public class ThreadPrint1 {
    private static final ReentrantLock LOCK = new ReentrantLock();
    /**
     * 当前即将打印的数字
     */
    private static int current = 0;

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
            while (true) {
                LOCK.lock();
                try {
                    // 判断是否轮到当前线程执行
                    while (current % threadCount == threadNo) {
                        if (current > maxInt) {
                            break;
                        }
                        System.out.println("thread" + threadNo + " : " + current);
                        current++;
                    }
                    // 最大值跳出循环
                    if (current > maxInt) {
                        break;
                    }
                }finally {
                    // 唤醒其他wait线程
                    LOCK.unlock();
                }
            }
        }

    }
    public static void main(String[] args) {
        int threadCount = 3;
        int max = 100;
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Printer(i, threadCount, max)).start();
        }
    }
}
