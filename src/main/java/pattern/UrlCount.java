package pattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-04-12
 * @since 1.0.0
 */
public class UrlCount {

    static class Query {
        public static final Query NONE = new Query();
        String origin;
        String domain;
        String url;
        String userId;

        Query() {
        }

        Query(String origin) {
            this.origin = origin;
            String[] result = origin.split(" ");
            this.url = result[0];
            this.userId = result[1];
            String domain = url.substring(0, url.indexOf("/"));
            this.domain = domain;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "origin='" + origin + '\'' +
                    '}';
        }
    }

    static class QueryProducer implements Runnable {
        String name;
        BlockingDeque<Query> queue;
        File file;
        CountDownLatch countDownLatch;

        QueryProducer(BlockingDeque<Query> queue, File file, CountDownLatch countDownLatch) {
            this.queue = queue;
            this.file = file;
            this.countDownLatch = countDownLatch;
            this.name = file.getName();
        }

        @Override
        public void run() {
            System.out.println("QueryProducer " + name + " started.");
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    queue.put(new Query(line));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("QueryProducer " + name + " finished.");
        }
    }

    static class QueryHashConsumer implements Runnable {
        String name;
        BlockingDeque<Query> queue;
        File[] queryHashFiles;
        CountDownLatch countDownLatch;

        public QueryHashConsumer(String name, BlockingDeque<Query> queue, File[] queryHashFiles, CountDownLatch countDownLatch) {
            this.name = name;
            this.queue = queue;
            this.queryHashFiles = queryHashFiles;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("QueryHashConsumer " + name + " started.");
            while (true) {
                try {
                    Query query = queue.take();
                    if (query == Query.NONE) {
                        queue.put(query);//put back to kill others
                        System.out.println("QueryHashConsumer " + name + " finished");
                        break;
                    }

                    int index = Math.abs(query.url.hashCode()) % queryHashFiles.length;
                    synchronized (queryHashFiles[index]) {
                        try (FileWriter writer = new FileWriter(queryHashFiles[index], true)) {
                            writer.write(query.origin);
                            writer.write(System.getProperty("line.separator"));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            countDownLatch.countDown();
        }
    }

    static class QueryHash {
        final ThreadPoolExecutor readThreadPoolExecutor;
        final ThreadPoolExecutor writeThreadPoolExecutor;
        final File[] sourceFiles;
        final BlockingDeque<Query> queue;
        final File urlHashedQueryDir;
        final int queryHashFileCount;

        public QueryHash(File[] sourceFiles, int readThreadCount, int writeThreadCount, int queueSize, File urlHashedQueryDir, int queryHashFileCount) {
            this.sourceFiles = sourceFiles;
            this.readThreadPoolExecutor = new ThreadPoolExecutor(readThreadCount, readThreadCount, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
            this.writeThreadPoolExecutor = new ThreadPoolExecutor(writeThreadCount, writeThreadCount, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
            this.queue = new LinkedBlockingDeque<>(queueSize);
            this.urlHashedQueryDir = urlHashedQueryDir;
            this.queryHashFileCount = queryHashFileCount;
        }

        public File[] hashWrite() {
            System.out.println("query hashed start");

            CountDownLatch producerCountDownLatch = new CountDownLatch(sourceFiles.length);
            for (File file : sourceFiles) {
                readThreadPoolExecutor.execute(new QueryProducer(queue, file, producerCountDownLatch));
            }

            if (!urlHashedQueryDir.exists()) {
                urlHashedQueryDir.mkdirs();
            }
            File[] queryHashFiles = new File[queryHashFileCount];
            for (int i = 0; i < queryHashFileCount; i++) {
                try {
                    File file = new File(urlHashedQueryDir, i + ".txt");
                    queryHashFiles[i] = file;
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            CountDownLatch consumerCountDownLatch = new CountDownLatch(writeThreadPoolExecutor.getMaximumPoolSize());
            for (int i = 0; i < writeThreadPoolExecutor.getMaximumPoolSize(); i++) {
                writeThreadPoolExecutor.execute(new QueryHashConsumer(i + "", queue, queryHashFiles, consumerCountDownLatch));
            }

            try {
                producerCountDownLatch.await();
                queue.put(Query.NONE);
                consumerCountDownLatch.await();
                readThreadPoolExecutor.shutdown();
                writeThreadPoolExecutor.shutdown();
                System.out.println("query hashed finisher");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return queryHashFiles;
        }
    }

    static class UrlCounter {
        private final String domain;
        private final String url;
        Set<String> userIds = new HashSet<>();
        int pv = 0;

        public UrlCounter(Query query) {
            this.domain = query.domain;
            this.url = query.url;
        }

        public void addUserId(String userId) {
            userIds.add(userId);
            pv++;
        }

        @Override
        public String toString() {
            return "UrlCountResult{" +
                    "domain='" + domain + '\'' +
                    ", url='" + url + '\'' +
                    ", pv=" + pv +
                    ", userIds=" + userIds +
                    '}';
        }
    }

    static class UrlCountConsumer implements Runnable {
        private final String name;
        private final File urlHashedQueryFile;
        private final File urlCountResultFile;
        private final CountDownLatch countDownLatch;
        private final Map<String, UrlCounter> urlToCountResults = new HashMap<>();

        UrlCountConsumer(String name, File urlHashedQueryFile, File urlCountResultFile, CountDownLatch countDownLatch) {
            this.name = name;
            this.urlHashedQueryFile = urlHashedQueryFile;
            this.urlCountResultFile = urlCountResultFile;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("UrlCountConsumer " + name + " started.");
            try (BufferedReader reader = new BufferedReader(new FileReader(urlHashedQueryFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!System.getProperty("line.separator").equals(line)) {
                        Query query = new Query(line);
                        if (!urlToCountResults.containsKey(query.url)) {
                            urlToCountResults.putIfAbsent(query.url, new UrlCounter(query));
                        }
                        UrlCounter urlCounter = urlToCountResults.get(query.url);
                        urlCounter.addUserId(query.userId);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            synchronized (urlCountResultFile) {
                try (FileWriter writer = new FileWriter(urlCountResultFile, true)) {
                    for (Map.Entry<String, UrlCounter> entry : urlToCountResults.entrySet()) {
                        UrlCounter urlCounter = entry.getValue();
                        writer.write(urlCounter.url + " " + urlCounter.userIds.size() + " " + urlCounter.pv);
                        writer.write(System.getProperty("line.separator"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            countDownLatch.countDown();
            System.out.println("UrlCountConsumer " + name + " end.");
        }
    }

    static class UrlCountTask {
        private final ThreadPoolExecutor threadPoolExecutor;
        private final File[] urlHashedQueryFiles;
        private final File urlCountResultFile;

        public UrlCountTask(File[] urlHashedQueryFiles, File urlCountResultFile, int threadCount) {
            this.urlHashedQueryFiles = urlHashedQueryFiles;
            this.urlCountResultFile = urlCountResultFile;
            this.threadPoolExecutor = new ThreadPoolExecutor(threadCount, threadCount, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        }

        public File count() {
            System.out.println("url count start");

            CountDownLatch producerCountDownLatch = new CountDownLatch(urlHashedQueryFiles.length);
            for (int i = 0; i < urlHashedQueryFiles.length; i++) {
                threadPoolExecutor.execute(new UrlCountConsumer(i + "", urlHashedQueryFiles[i], urlCountResultFile, producerCountDownLatch));
            }


            try {
                producerCountDownLatch.await();
                threadPoolExecutor.shutdown();
                System.out.println("url count finisher");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return urlCountResultFile;
        }
    }

    static class UrlCountResult {
        private final String domain;
        private final String url;
        private final int uv;
        private final int pv;

        public UrlCountResult(String string) {
            String[] result = string.split(" ");
            this.url = result[0];
            this.uv = Integer.parseInt(result[1]);
            this.pv = Integer.parseInt(result[2]);

            String domain = url.substring(0, url.indexOf("/"));
            this.domain = domain;
        }

        public String getDomain() {
            return domain;
        }

        public String getUrl() {
            return url;
        }

        public int getUv() {
            return uv;
        }

        public int getPv() {
            return pv;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UrlCountResult that = (UrlCountResult) o;
            return uv == that.uv && pv == that.pv && Objects.equals(domain, that.domain) && Objects.equals(url, that.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(domain, url, uv, pv);
        }

        @Override
        public String toString() {
            return "DomainTopNumberParam{" +
                    "domain='" + domain + '\'' +
                    ", url='" + url + '\'' +
                    ", uv=" + uv +
                    ", pv=" + pv +
                    '}';
        }
    }

    static class MinHeap<T> {
        private final PriorityQueue<T> minHeap;
        private final int maxSize;
        private final Comparator<T> comparator;

        public MinHeap(int maxSize, Comparator<T> comparator) {
            this.maxSize = maxSize;
            this.comparator = comparator;
            this.minHeap = new PriorityQueue<>(maxSize, comparator);
        }

        public void insert(T element) {
            if (minHeap.size() < maxSize) {
                minHeap.offer(element);
                //如果堆顶元素 < 新数，则删除堆顶，加入新数入堆
            } else if (comparator.compare(minHeap.peek(), element) < 0) {
                minHeap.poll();
                minHeap.offer(element);
            }
        }

        public List<T> getTop(int topNumber) {
            List<T> topList = new ArrayList<>(topNumber);
            for (int i = 0; i < topNumber && !minHeap.isEmpty(); i++) {
                topList.add(minHeap.poll());
            }
            return topList;
        }
    }

    static class DomainTopNumberTask {
        private final int topNumber;
        private final Comparator<UrlCountResult> comparator;
        private final File urlCountResultFile;
        private final Function<UrlCountResult, String> domainFunction;

        public DomainTopNumberTask(int topNumber, Comparator<UrlCountResult> comparator, File urlCountResultFile, Function<UrlCountResult, String> domainFunction) {
            this.topNumber = topNumber;
            this.comparator = comparator;
            this.urlCountResultFile = urlCountResultFile;
            this.domainFunction = domainFunction;
        }

        public Map<String, List<UrlCountResult>> getTopN() {
            Map<String, MinHeap<UrlCountResult>> domainToMinHeap = new HashMap<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(urlCountResultFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!System.getProperty("line.separator").equals(line)) {
                        UrlCountResult urlCountResult = new UrlCountResult(line);
                        String domain = domainFunction.apply(urlCountResult);
                        if (!domainToMinHeap.containsKey(domain)) {
                            domainToMinHeap.putIfAbsent(domain, new MinHeap<>(topNumber, comparator));
                        }
                        MinHeap<UrlCountResult> minHeap = domainToMinHeap.get(domain);
                        minHeap.insert(urlCountResult);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Map<String, List<UrlCountResult>> result = new HashMap<>();
            for (Map.Entry<String, MinHeap<UrlCountResult>> entry : domainToMinHeap.entrySet()) {
                result.put(entry.getKey(), entry.getValue().getTop(topNumber));
            }
            return result;
        }
    }


    public static void main(String[] args) {
        File file = new File("urlHashedQuery");
        //将query数据根据url hash输出到小文件保证所有相同url处于相同文件
        QueryHash queryHash = new QueryHash(new File("query").listFiles(), 8, 8, 100000, file, 100);
        File[] urlHashedQueryFiles = queryHash.hashWrite();
        //对按url hash后的文件进行根据url统计userIdSet和pv,每个文件统计完后,将统计结果的url+uv+pv写入指定文件
        UrlCountTask urlCountTask = new UrlCountTask(urlHashedQueryFiles, new File("urlCountResult.txt"), 5);
        File urlCountResultFile = urlCountTask.count();
        //读取统计结果的指定文件,根据域名分配最小堆,进行堆排序
        DomainTopNumberTask domainTopNumberTask = new DomainTopNumberTask(10, Comparator.comparingInt(urlCountResult -> urlCountResult.uv), urlCountResultFile, UrlCountResult::getDomain);
        //按域名获取topK的url
        Map<String, List<UrlCountResult>> domainToTopN = domainTopNumberTask.getTopN();
        for (Map.Entry<String, List<UrlCountResult>> entry : domainToTopN.entrySet()) {
            System.out.println(entry.getKey() + "top start" );
            for(UrlCountResult urlCountResult: entry.getValue()){
                System.out.println(urlCountResult);
            }
            System.out.println(entry.getKey() + "top end" );
        }

        //todo 使用RandomAccessFile实现一个文件多线程读取
        //todo 假如一个url过于热点,userIdSet过大,单机内存无法放下.方案:使用bloomFilter+count变量统计uv,有误判,损失精确度.无损方案?
    }
}
