package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV1 {

    public static void main(String[] args) {
        Thread thread = new Thread(new ParkTask(), "Thread-1");
        thread.start();

        sleep(100);
        log("Thread-1 state: " + thread.getState());

        log("main -> unpark(thread-1)");
//        LockSupport.unpark(thread);
        thread.interrupt();
    }

    static class ParkTask implements Runnable {
        @Override
        public void run() {
            log("park 시작");
            LockSupport.park();
            log("park 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        }
    }
}
