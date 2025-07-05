package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class PrestartPoolMain {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1_000);

        ExecutorUtils.printState(es);
        ThreadPoolExecutor pollExecutor = (ThreadPoolExecutor) es;
        pollExecutor.prestartAllCoreThreads();
        ExecutorUtils.printState(es);

        es.close();
    }
}
