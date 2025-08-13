package parallel;

import static util.MyLogger.log;

public class HeavyJob {

    public static int heavyTask(int i) {
        log("calculate " + i + " -> " + i * 10);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return i * 10;
    }

    public static int heavyTask(int i, String name) {
        log("[" + name + "] " + i + " -> " + i * 10);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return i * 10;
    }
}
