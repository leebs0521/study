package thread.start.test;

import static util.MyLogger.log;

public class StartTest3Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    log("value: " + i);
                }
            }
        }, "counter");

        thread.start();
    }
}
