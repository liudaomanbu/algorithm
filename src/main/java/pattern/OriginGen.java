package pattern;

import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author caotc
 * @date 2021-04-13
 * @since 1.0.0
 */
public class OriginGen {
    private static final String[] DOMAINS = new String[]{"www.taobao.com", "www.tmall.com"};
    private static final String[] PATHS = new String[]{"item", "search"};

    public static void main(String[] args) {
        genFiles(10,100000000);
    }

    public static void genFiles(int fileNumber,int lineNumber) {
        IntStream.range(0, fileNumber)
                .parallel()
                .forEach((i)->genFile(i,lineNumber));
    }

    public static void genFile(int fileNumber,int lineNumber) {
        File file = new File("query/origin" + fileNumber+".txt");
        try (FileWriter writer = new FileWriter(file, true)) {
            for (int i = 0; i < lineNumber; i++) {
                writer.write(genLine());
                writer.write(System.getProperty("line.separator"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String genUrl() {
        Random random = new Random();
        return DOMAINS[random.nextInt(2)] + "/" + PATHS[random.nextInt(2)] + "?id=" + random.nextInt(10000);
    }

    public static String genLine() {
        return genUrl() + " " + new Random().nextInt(10000);
    }
}
