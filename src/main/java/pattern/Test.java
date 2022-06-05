package pattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author caotc
 * @date 2021-04-12
 * @since 1.0.0
 */
public class Test {
    static class Pv {
        String domain;
        String url;
        String userId;

        Pv(String domain, String url, String userId) {
            this.domain = domain;
            this.url = url;
            this.userId = userId;
        }
    }

    static class Producer implements Runnable {
        BlockingDeque<Pv> queue;
        File file;

        Producer(BlockingDeque<Pv> queue, File file) {
            this.queue = queue;
            this.file = file;
        }

        public Pv parse(String string){
            String[] result = string.split(" ");
            String url = result[0];
            String userId = result[1];
            String domain = url.substring(url.indexOf("/"));
            return new Pv(domain, url, userId);
        }

        public void product() {
            if (file.isFile()) {
                FileReader fr = null;
                BufferedReader bf = null;
                try {
                    fr = new FileReader(file);
                    bf = new BufferedReader(fr);
                    String string;
                    while ((string = bf.readLine()) != null) {
                        queue.put(parse(string));
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        bf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void run() {
            product();
        }
    }

    static class CountResult{
        Set<String> userIdSet=new HashSet<>();
        AtomicLong pvCount=new AtomicLong();

        public long getUvCount() {
            return userIdSet.size();
        }

        public long getPvCount() {
            return pvCount.get();
        }
    }

    static class Consumer implements Runnable{
        BlockingDeque<Pv> queue;
        Map<String, TreeMap<String, CountResult>> domainToCountResultMap;

        Consumer(BlockingDeque<Pv> queue, Map<String, TreeMap<String, CountResult>> domainToCountResultMap) {
            this.queue = queue;
            this.domainToCountResultMap = domainToCountResultMap;
        }

        public void consume(){
            try {
                Pv pv = queue.take();
                domainToCountResultMap.putIfAbsent(pv.domain, new TreeMap<>());
                TreeMap<String, CountResult> urlToUv = domainToCountResultMap.get(pv.domain);
                urlToUv.putIfAbsent(pv.url, new CountResult());
                CountResult countResult=urlToUv.get(pv.url);
                countResult.userIdSet.add(pv.userId);
                countResult.pvCount.incrementAndGet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true){
                consume();
            }
        }
    }

    public void countUv(File file) {
        BlockingDeque<Pv> queue=new LinkedBlockingDeque<>();
        List<Producer> producers=createProducer(file,queue);
        Map<String, TreeMap<String, CountResult>> domainToCountMap = new ConcurrentHashMap<>();
        List<Consumer> consumers=new ArrayList<>();
        for(int i=0;i<5;i++){
            consumers.add(new Consumer(queue,domainToCountMap));
        }

        for(Map.Entry<String, TreeMap<String, CountResult>> entry:domainToCountMap.entrySet()){
            System.out.println("domain:"+entry.getKey());
            TreeMap<String, CountResult> urlToCountResults = entry.getValue();

        }
    }

    public List<Producer> createProducer(File file,BlockingDeque<Pv> queue) {
        List<Producer> producers=new ArrayList<>();
        if (file.isFile()) {
            producers.add(new Producer(queue, file));
            return producers;
        } else {
            if (Objects.nonNull(file.listFiles())) {
                for (File f : file.listFiles()) {
                    producers.addAll(createProducer(f,queue));
                }
            }
            return producers;
        }
    }

}
