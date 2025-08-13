
package parallel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import static util.MyLogger.log;

public class ParallelMain5 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int processorCount = Runtime.getRuntime().availableProcessors();
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        log("processorCount = " + processorCount + ", commonPool = " + commonPool.getParallelism());

        long startTime = System.currentTimeMillis();
        int sum = IntStream.rangeClosed(1, 8)
                .parallel() // 추가
                .map(HeavyJob::heavyTask)
                .reduce(0, Integer::sum);
        long endTime = System.currentTimeMillis();

        log("time: " + (endTime - startTime) + "ms, sum: " + sum);
    }
}
