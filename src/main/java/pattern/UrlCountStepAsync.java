package pattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author caotc
 * @date 2021-04-12
 * @since 1.0.0
 */
public class UrlCountStepAsync {

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
            String domain=null;
            try{
                 domain = url.substring(0, url.indexOf("/"));
            }catch (Exception e){
                System.out.println(url);
            }
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

    static class UrlCountConsumer implements Callable<File> {
        private final String name;
        private final File urlHashedQueryFile;
        private final File urlCountResultDir;
        private final Map<String, UrlCounter> urlToCountResults = new HashMap<>();

        UrlCountConsumer(String name, File urlHashedQueryFile, File urlCountResultDir) {
            this.name = name;
            this.urlHashedQueryFile = urlHashedQueryFile;
            this.urlCountResultDir = urlCountResultDir;
        }

        @Override
        public File call() throws Exception {
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
            File urlCountResultFile=new File(urlCountResultDir,name);
            try (FileWriter writer = new FileWriter(urlCountResultFile, true)) {
                for (Map.Entry<String, UrlCounter> entry : urlToCountResults.entrySet()) {
                    UrlCounter urlCounter = entry.getValue();
                    writer.write(urlCounter.url + " " + urlCounter.userIds.size() + " " + urlCounter.pv);
                    writer.write(System.getProperty("line.separator"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("UrlCountConsumer " + name + " end.");
            return urlCountResultFile;
        }
    }

    public static CompletableFuture<File> getUrlCountResultFile(Executor executor,File urlHashedQueryFile,File urlCountResultDir){
        UrlCountConsumer domainTopNumberTask = new UrlCountConsumer(urlHashedQueryFile.getName(),urlHashedQueryFile, urlCountResultDir);
        return asFuture(domainTopNumberTask,executor);
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

        public MinHeap<T> merge(MinHeap<T> other) {
            other.getTop(maxSize).forEach(this::insert);
            return this;
        }
    }

    static class DomainTopNumberTask implements Callable<Map<String, MinHeap<UrlCountResult>>> {
        private final String name;
        private final int topNumber;
        private final Comparator<UrlCountResult> comparator;
        private final File urlCountResultFile;
        private final Function<UrlCountResult, String> domainFunction;

        public DomainTopNumberTask(String name, int topNumber, Comparator<UrlCountResult> comparator, File urlCountResultFile, Function<UrlCountResult, String> domainFunction) {
            this.name=name;
            this.topNumber = topNumber;
            this.comparator = comparator;
            this.urlCountResultFile = urlCountResultFile;
            this.domainFunction = domainFunction;
        }

        public Map<String, MinHeap<UrlCountResult>> getTopN() {
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
            return domainToMinHeap;
        }

        @Override
        public Map<String, MinHeap<UrlCountResult>> call() throws Exception {
            System.out.println("DomainTopNumberTask "+name+" started");
            Map<String, MinHeap<UrlCountResult>> domainToMinHeap = getTopN();
            System.out.println("DomainTopNumberTask "+name+" finished");
            return domainToMinHeap;
        }
    }

    public static final Comparator<UrlCountResult> UV_COMPARATOR=Comparator.comparingInt(urlCountResult -> urlCountResult.uv);
    public static final Comparator<UrlCountResult> PV_COMPARATOR=Comparator.comparingInt(urlCountResult -> urlCountResult.pv);

    public static CompletableFuture<Map<String, MinHeap<UrlCountResult>>> getDomainToMinHeap(Executor executor,File urlCountResultFile,int topNumber,Comparator<UrlCountResult> comparator,Function<UrlCountResult, String> domainFunction){
        DomainTopNumberTask domainTopNumberTask = new DomainTopNumberTask(urlCountResultFile.getName(), topNumber, comparator, urlCountResultFile, domainFunction);
        return asFuture(domainTopNumberTask,executor);
    }

    public static void main(String[] args) {
        File urlHashedQueryDir = new File("urlHashedQuery");
        if(!urlHashedQueryDir.exists()){
            urlHashedQueryDir.mkdirs();
        }
        int queryHashFileCount=100;
        //将query数据根据url hash输出到小文件保证所有相同url处于相同文件
        QueryHash queryHash = new QueryHash(new File("query").listFiles(), 8, 8, 100000, urlHashedQueryDir, queryHashFileCount);
        File[] urlHashedQueryFiles = queryHash.hashWrite();
        File urlCountResultDir = new File("urlCountResult");
        if(!urlCountResultDir.exists()){
            urlCountResultDir.mkdirs();
        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(12, 12, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        int topNumber=10;
        Comparator<UrlCountResult> comparator=UV_COMPARATOR;
        Function<UrlCountResult, String> domainFunction=UrlCountResult::getDomain;
        Map<String, MinHeap<UrlCountResult>> mergedDomainToMinHeap = Arrays.stream(urlHashedQueryFiles)
                //对按url hash后的文件进行根据url统计userIdSet和pv,每个文件统计完后,将统计结果的url+uv+pv写入指定文件
                .map(urlHashedQueryFile -> getUrlCountResultFile(threadPoolExecutor, urlHashedQueryFile, urlCountResultDir))
                //读取统计结果的指定文件,根据域名分配最小堆,进行堆排序
                .map(urlCountResultCompletableFuture -> urlCountResultCompletableFuture.thenCompose(urlCountResultFile -> getDomainToMinHeap(threadPoolExecutor, urlCountResultFile, topNumber, comparator, domainFunction)))
                .map(CompletableFuture::join)
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        MinHeap::merge));
        threadPoolExecutor.shutdown();
        Map<String, List<UrlCountStepAsync.UrlCountResult>> domainToTopN = new HashMap<>();
        for (Map.Entry<String, UrlCountStepAsync.MinHeap<UrlCountStepAsync.UrlCountResult>> entry : mergedDomainToMinHeap.entrySet()) {
            domainToTopN.put(entry.getKey(), entry.getValue().getTop(topNumber));
        }

        //按域名获取topK的url
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

    public static <T> CompletableFuture<T> asFuture(Callable<? extends T> callable, Executor executor) {
        CompletableFuture<T> future = new CompletableFuture<>();
        executor.execute(() -> {
            try {
                future.complete(callable.call());
            } catch (Throwable t) {
                future.completeExceptionally(t);
            }
        });
        return future;
    }
}
